package phoneisure.infrastructure.persistence.hibernate.merchant;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import phoneisure.domain.model.merchant.IMerchantRepository;
import phoneisure.domain.model.merchant.Merchant;
import phoneisure.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by YJH on 2016/4/21.
 */
@Repository("merchantRepository")
public class MerchantRepository extends AbstractHibernateGenericRepository<Merchant, String>
        implements IMerchantRepository<Merchant, String> {
    @Override
    public Merchant searchByArea(String areaId, String appKey) {
        Criteria criteria = getSession().createCriteria(this.getPersistentClass());
        criteria.add(Restrictions.eq("area.id", areaId));
        criteria.add(Restrictions.eq("appKey.id", appKey));
        return (Merchant) criteria.uniqueResult();
    }

    @Override
    public Merchant searchByUserName(String parentAgent, String appKey) {
        Criteria criteria = getSession().createCriteria(this.getPersistentClass());
        criteria.add(Restrictions.eq("userName", parentAgent));
        criteria.add(Restrictions.eq("appKey.name", appKey));
        criteria.createAlias("appKey", "appKey");
        return (Merchant) criteria.uniqueResult();
    }

    @Override
    public Merchant searchByUserName(String userName) {
        Criteria criteria = getSession().createCriteria(this.getPersistentClass());
        criteria.add(Restrictions.eq("userName", userName));
        return (Merchant) criteria.uniqueResult();
    }

    @Override
    public BigDecimal userMoney(List<String> ids) {
        Criteria criteria = getSession().createCriteria(this.getPersistentClass());
        if (null != ids.get(0) && ids.size() > 0) {
            criteria.add(Restrictions.in("agent.id", ids));
        }
        criteria.setProjection(Projections.sum("money"));
        return (BigDecimal) criteria.uniqueResult();
    }
}
