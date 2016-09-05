package phoneisure.domain.model.policy;

import phoneisure.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by YJH on 2016/4/22.
 */
public interface IPolicyRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
    List<Policy> searchByIMEI(String imei);
}
