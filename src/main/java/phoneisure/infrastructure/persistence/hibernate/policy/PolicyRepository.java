package phoneisure.infrastructure.persistence.hibernate.policy;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import phoneisure.domain.model.policy.IPolicyRepository;
import phoneisure.domain.model.policy.Policy;
import phoneisure.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;

import java.util.List;

/**
 * Created by YJH on 2016/4/22.
 */
@Repository("policyRepository")
public class PolicyRepository extends AbstractHibernateGenericRepository<Policy, String>
        implements IPolicyRepository<Policy, String> {
    @Override
    public List<Policy> searchByIMEI(String imei) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("IMEI", imei));
        return criteria.list();
    }
}
