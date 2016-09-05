package phoneisure.domain.model.merchant;

import phoneisure.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by YJH on 2016/4/21.
 */
public interface IMerchantRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
    Merchant searchByArea(String areaId, String appKey);

    Merchant searchByUserName(String parentAgent, String appKey);

    Merchant searchByUserName(String userName);

    BigDecimal userMoney(List<String> ids);

}
