package phoneisure.infrastructure.persistence.hibernate.appkey;


import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import phoneisure.domain.model.appkey.AppKey;
import phoneisure.domain.model.appkey.IAppKeyRepository;
import phoneisure.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/3/30.
 */
@Repository("appKeyRepository")
public class AppKeyRepository extends AbstractHibernateGenericRepository<AppKey, String>
        implements IAppKeyRepository<AppKey, String> {
    @Override
    public AppKey searchByName(String name) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("name", name));
        return (AppKey) criteria.uniqueResult();
    }
}
