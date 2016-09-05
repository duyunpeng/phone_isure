package phoneisure.infrastructure.persistence.hibernate.policyfee;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import phoneisure.domain.model.phonebrand.PhoneBrand;
import phoneisure.domain.model.policyfee.IPolicyFeeRepository;
import phoneisure.domain.model.policyfee.PolicyFee;
import phoneisure.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/4/22.
 */
@Repository("policyFeeRepository")
public class PolicyFeeRepository extends AbstractHibernateGenericRepository<PolicyFee, String>
        implements IPolicyFeeRepository<PolicyFee, String> {
    @Override
    public PolicyFee getByPhoneModel(String phoneModel) {
        Criteria criteria=getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("phoneModel",phoneModel));
        return (PolicyFee) criteria.uniqueResult();
    }
}
