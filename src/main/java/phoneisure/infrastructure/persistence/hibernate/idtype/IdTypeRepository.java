package phoneisure.infrastructure.persistence.hibernate.idtype;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import phoneisure.domain.model.idtype.IIdTypeRepository;
import phoneisure.domain.model.idtype.IdType;
import phoneisure.infrastructure.persistence.hibernate.generic.AbstractHibernateGenericRepository;

/**
 * Created by YJH on 2016/4/22.
 */
@Repository("idTypeRepository")
public class IdTypeRepository extends AbstractHibernateGenericRepository<IdType, String>
        implements IIdTypeRepository<IdType, String> {
    @Override
    public IdType searchByName(String name) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        criteria.add(Restrictions.eq("name",name));
        return (IdType) criteria.uniqueResult();
    }
}
