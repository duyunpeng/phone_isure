package phoneisure.domain.model.phonebrand;

import phoneisure.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by YJH on 2016/4/22.
 */
public interface IPhoneBrandRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {

    PhoneBrand getByName(String name);
}
