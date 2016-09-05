package phoneisure.infrastructure.persistence.hibernate.moneydetailed;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import phoneisure.application.moneydetailed.command.ListMoneyDetailedCommand;
import phoneisure.core.enums.FlowType;
import phoneisure.core.util.CoreDateUtils;
import phoneisure.core.util.CoreStringUtils;
import phoneisure.domain.model.moneydetailed.IMoneyDetailedRepository;
import phoneisure.domain.model.moneydetailed.MoneyDetailed;
import phoneisure.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YJH on 2016/4/21.
 */
@Repository("moneyDetailedRepository")
public class MoneyDetailedRepository extends AbstractHibernateGenericRepository<MoneyDetailed, String>
        implements IMoneyDetailedRepository<MoneyDetailed, String> {
    @Override
    public BigDecimal count(ListMoneyDetailedCommand command, FlowType flowType,List<String> ids) {
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

        if (!CoreStringUtils.isEmpty(command.getAgent())) {
            criteria.add(Restrictions.in("merchant.agent.id", ids));
            criteria.createAlias("merchant", "merchant");
        }

        criteria.add(Restrictions.eq("flowType", flowType));
        criteria.setProjection(Projections.sum("money"));
        return (BigDecimal) criteria.uniqueResult();
    }
}
