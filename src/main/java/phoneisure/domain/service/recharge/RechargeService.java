package phoneisure.domain.service.recharge;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phoneisure.application.recharge.command.ListRechargeCommand;
import phoneisure.application.recharge.command.RechargeCommand;
import phoneisure.core.commons.id.IdFactory;
import phoneisure.core.enums.*;
import phoneisure.core.pay.wechat.WechatNotify;
import phoneisure.core.util.CoreDateUtils;
import phoneisure.core.util.CoreStringUtils;
import phoneisure.domain.model.merchant.Merchant;
import phoneisure.domain.model.recharge.IRechargeRepository;
import phoneisure.domain.model.recharge.Recharge;
import phoneisure.domain.service.merchant.IMerchantService;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dyp on 2016/4/22.
 */
@Service("rechargeService")
public class RechargeService implements IRechargeService {

    @Autowired
    private IRechargeRepository<Recharge, String> rechargeRepository;

    @Autowired
    private IMerchantService merchantService;

    @Autowired
    private IdFactory idFactory;

    @Override
    public Pagination<Recharge> pagination(ListRechargeCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        Map<String, String> aliasMap = new HashMap<String, String>();

        if (!CoreStringUtils.isEmpty(command.getStartDealTime()) && null != CoreDateUtils.parseDate(command.getStartDealTime(),"yyyy/MM/dd HH:mm")) {
            criterionList.add(Restrictions.ge("createDate", CoreDateUtils.parseDate(command.getStartDealTime(),"yyyy/MM/dd HH:mm")));
        }
        if (!CoreStringUtils.isEmpty(command.getEndDealTime()) && null != CoreDateUtils.parseDate(command.getEndDealTime(),"yyyy/MM/dd HH:mm")) {
            criterionList.add(Restrictions.le("createDate", CoreDateUtils.parseDate(command.getEndDealTime(),"yyyy/MM/dd HH:mm")));
        }
        if (null != command.getIsPay() && command.getIsPay() != YesOrNoStatus.ALL.ALL) {
            criterionList.add(Restrictions.eq("isPay", command.getIsPay()));
        }
        if (!CoreStringUtils.isEmpty(command.getMerchant())) {
            criterionList.add(Restrictions.like("merchant.userName", command.getMerchant(), MatchMode.ANYWHERE));
            aliasMap.put("merchant", "merchant");
        }
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));
        return rechargeRepository.pagination(command.getPage(), command.getPageSize(), criterionList, aliasMap, orderList, null);
    }

    @Override
    public List<Recharge> list(ListRechargeCommand command) {

        List<Criterion> criterionList = new ArrayList<Criterion>();

        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.asc("createDate"));
        return rechargeRepository.list(criterionList, orderList);
    }

    @Override
    public Recharge apiRecharge(RechargeCommand command) {
        String no = idFactory.getNextId();
        Merchant merchant = merchantService.searchByID(command.getMerchant());
        Recharge recharge = new Recharge(no, merchant, command.getMoney(), null, PayType.WECHAT, null, YesOrNoStatus.NO, RechargeStatus.WAIT, command.getIpAddress());
        rechargeRepository.save(recharge);
        return recharge;
    }

    @Override
    public void apiWechatSuccess(WechatNotify notify) {
        Recharge recharge = this.searchByNo(notify.getOut_trade_no());
        Merchant merchant = merchantService.searchByID(recharge.getMerchant().getId());
        if (null != recharge && null == recharge.getPayDate() && notify.getTotal_fee() == recharge.getRechargeMoney().multiply(new BigDecimal(100)).intValue()) {
            recharge.changePayDate(CoreDateUtils.parseDate(notify.getTime_end(), "yyyyMMddHHmmss"));
            recharge.changeStatus(RechargeStatus.SUCCESS);
            recharge.changePayNo(notify.getTransaction_id());
            recharge.changeIsPay(YesOrNoStatus.YES);
            recharge.changePayType(PayType.WECHAT);
            merchant.changeMoney(merchant.getMoney().add(recharge.getRechargeMoney()));
            merchantService.update(merchant);
        }
        rechargeRepository.update(recharge);
    }

    @Override
    public Recharge searchByNo(String rechargeNo) {
        return rechargeRepository.searchByNo(rechargeNo);
    }

    @Override
    public BigDecimal count(ListRechargeCommand command) {
        BigDecimal count =  rechargeRepository.count(command, YesOrNoStatus.YES);
        return count;
    }
}
