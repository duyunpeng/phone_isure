package phoneisure.domain.model.policyfee;

import phoneisure.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by YJH on 2016/4/22.
 */
public interface IPolicyFeeRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {

    PolicyFee getByPhoneModel(String phoneModel);
}
