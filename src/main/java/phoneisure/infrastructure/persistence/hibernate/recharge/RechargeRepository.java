package phoneisure.infrastructure.persistence.hibernate.recharge;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import phoneisure.application.recharge.command.ListRechargeCommand;
import phoneisure.core.enums.YesOrNoStatus;
import phoneisure.core.util.CoreDateUtils;
import phoneisure.core.util.CoreStringUtils;
import phoneisure.domain.model.recharge.IRechargeRepository;
import phoneisure.domain.model.recharge.Recharge;
import phoneisure.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;

import java.math.BigDecimal;

/**
 * Created by YJH on 2016/4/21.
 */
@Repository("rechargeRepository")
public class RechargeRepository extends AbstractHibernateGenericRepository<Recharge, String>
        implements IRechargeRepository<Recharge, String> {
    @Override
    public Recharge searchByNo(String rechargeNo) {
        Criteria criteria = getSession().createCriteria(this.getPersistentClass());
        criteria.add(Restrictions.eq("rechargeNo", rechargeNo));
        return (Recharge) criteria.uniqueResult();
    }

    @Override
    public BigDecimal count(ListRechargeCommand command, YesOrNoStatus isPay) {
        Criteria criteria = getSession().createCriteria(this.getPersistentClass());
        if (!CoreStringUtils.isEmpty(command.getStartDealTime()) && null != CoreDateUtils.parseDate(command.getStartDealTime(), "yyyy/MM/dd HH:mm")) {
            criteria.add(Restrictions.ge("createDate", CoreDateUtils.parseDate(command.getStartDealTime(), "yyyy/MM/dd HH:mm")));
        }
        if (!CoreStringUtils.isEmpty(command.getEndDealTime()) && null != CoreDateUtils.parseDate(command.getEndDealTime(), "yyyy/MM/dd HH:mm")) {
            criteria.add(Restrictions.le("createDate", CoreDateUtils.parseDate(command.getEndDealTime(), "yyyy/MM/dd HH:mm")));
        }
        if (!CoreStringUtils.isEmpty(command.getMerchant())) {
            criteria.add(Restrictions.like("merchant.userName", command.getMerchant(), MatchMode.ANYWHERE));
            criteria.createAlias("merchant", "merchant");
        }
        criteria.add(Restrictions.eq("isPay", YesOrNoStatus.YES));
        criteria.setProjection(Projections.sum("rechargeMoney"));
        return (BigDecimal) criteria.uniqueResult();
    }
}
