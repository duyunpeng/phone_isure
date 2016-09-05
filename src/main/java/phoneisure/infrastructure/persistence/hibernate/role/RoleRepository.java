package phoneisure.infrastructure.persistence.hibernate.role;


import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import phoneisure.domain.model.role.IRoleRepository;
import phoneisure.domain.model.role.Role;
import phoneisure.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/3/30.
 */
@Repository("roleRepository")
public class RoleRepository extends AbstractHibernateGenericRepository<Role, String>
        implements IRoleRepository<Role, String> {
    @Override
    public Role searchByName(String name, String appKey) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("name", name));
        criteria.add(Restrictions.eq("appKey.name", appKey));
        criteria.createAlias("appKey", "appKey");
        return (Role) criteria.uniqueResult();
    }
}
