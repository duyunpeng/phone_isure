package phoneisure.domain.model.picture;

import phoneisure.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by YJH on 2016/4/12.
 */
public interface IPictureRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
}
