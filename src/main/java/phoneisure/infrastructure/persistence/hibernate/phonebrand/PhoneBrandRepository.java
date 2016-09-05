package phoneisure.infrastructure.persistence.hibernate.phonebrand;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import phoneisure.domain.model.phonebrand.IPhoneBrandRepository;
import phoneisure.domain.model.phonebrand.PhoneBrand;
import phoneisure.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;

/**
 * Created by Administrator on 2016/4/22.
 */
@Repository("phoneBrandRepository")
public class PhoneBrandRepository extends AbstractHibernateGenericRepository<PhoneBrand, String>
        implements IPhoneBrandRepository<PhoneBrand, String> {
    @Override
    public PhoneBrand getByName(String name) {
        Criteria criteria=getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("name",name));
        return (PhoneBrand) criteria.uniqueResult();
    }
}
