package phoneisure.domain.model.account;

import phoneisure.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;

/**
 * Created by YJH on 2016/3/30.
 */
public interface IAccountRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
    Account searchByAccountName(String userName, String appKey);

    Account searchByAccountName(String userName);
}
