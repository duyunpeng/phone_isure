package phoneisure.domain.model.idtype;

import phoneisure.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by YJH on 2016/4/22.
 */
public interface IIdTypeRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
    IdType searchByName(String name);
}
