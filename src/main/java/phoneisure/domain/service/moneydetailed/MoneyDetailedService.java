package phoneisure.domain.service.moneydetailed;

import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phoneisure.application.moneydetailed.command.CreateMoneyDetailedCommand;
import phoneisure.application.moneydetailed.command.ListMoneyDetailedCommand;
import phoneisure.core.enums.FlowType;
import phoneisure.core.util.CoreDateUtils;
import phoneisure.core.util.CoreStringUtils;
import phoneisure.domain.model.merchant.Merchant;
import phoneisure.domain.model.moneydetailed.IMoneyDetailedRepository;
import phoneisure.domain.model.moneydetailed.MoneyDetailed;
import phoneisure.domain.service.merchant.IMerchantService;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dyp on 16-4-22.
 */
@Service("moneyDetailedService")
public class MoneyDetailedService implements IMoneyDetailedService {

    @Autowired
    private IMoneyDetailedRepository<MoneyDetailed, String> moneyDetailedRepository;

    @Autowired
    private IMerchantService merchantService;

    @Override
    public Pagination<MoneyDetailed> pagination(ListMoneyDetailedCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        Map<String, String> aliasMap = new HashMap<String, String>();

        if (!CoreStringUtils.isEmpty(command.getStartDealTime()) && null != CoreDateUtils.parseDate(command.getStartDealTime(), "yyyy/MM/dd HH:mm")) {
            criterionList.add(Restrictions.ge("createDate", CoreDateUtils.parseDate(command.getStartDealTime(), "yyyy/MM/dd HH:mm")));
        }
        if (!CoreStringUtils.isEmpty(command.getEndDealTime()) && null != CoreDateUtils.parseDate(command.getEndDealTime(), "yyyy/MM/dd HH:mm")) {
            criterionList.add(Restrictions.le("createDate", CoreDateUtils.parseDate(command.getEndDealTime(), "yyyy/MM/dd HH:mm")));
        }
        if (!CoreStringUtils.isEmpty(command.getMerchant())) {
            criterionList.add(Restrictions.like("merchant.userName", command.getMerchant(), MatchMode.ANYWHERE));
            aliasMap.put("merchant", "merchant");
        }

        if (!CoreStringUtils.isEmpty(command.getAgent())) {
            List<String> ids = merchantService.getChildrenId(new ArrayList<String>(), command.getAgent());
            criterionList.add(Restrictions.in("merchant.agent.id", ids));
            aliasMap.put("merchant", "merchant");
        }

        if (null != command.getFlowType() && command.getFlowType() != FlowType.ALL) {
            criterionList.add(Restrictions.eq("flowType", command.getFlowType()));
        }

        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));
        return moneyDetailedRepository.pagination(command.getPage(), command.getPageSize(), criterionList, aliasMap, orderList, null);
    }

    @Override
    public List<MoneyDetailed> list(ListMoneyDetailedCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();

        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.asc("createDate"));
        return moneyDetailedRepository.list(criterionList, orderList);
    }

    @Override
    public void create(CreateMoneyDetailedCommand command) {
        Merchant merchant = merchantService.searchByID(command.getMerchant());
        MoneyDetailed moneyDetailed = new MoneyDetailed(merchant, command.getFlowType(), command.getMoney(), command.getExplain(), command.getInsuredName());
        moneyDetailedRepository.save(moneyDetailed);
    }

    @Override
    public BigDecimal count(ListMoneyDetailedCommand command) {
        List<String> ids = merchantService.getChildrenId(new ArrayList<String>(), command.getAgent());
        BigDecimal in = moneyDetailedRepository.count(command, FlowType.IN_FLOW, ids);
        BigDecimal out = moneyDetailedRepository.count(command, FlowType.OUT_FLOW, ids);
        in = null == in ? new BigDecimal(0) : in;
        out = null == out ? new BigDecimal(0) : out;
        return out.subtract(in);
    }

    @Override
    public BigDecimal userMoney(ListMoneyDetailedCommand command) {
        List<String> ids = merchantService.getChildrenId(new ArrayList<String>(), command.getAgent());
        return merchantService.userMoney(ids);
    }
}
