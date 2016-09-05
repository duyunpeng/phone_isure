package phoneisure.domain.model.moneydetailed;

import phoneisure.application.moneydetailed.command.ListMoneyDetailedCommand;
import phoneisure.core.enums.FlowType;
import phoneisure.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by YJH on 2016/4/21.
 */
public interface IMoneyDetailedRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
    BigDecimal count(ListMoneyDetailedCommand command, FlowType flowType, List<String> ids);
}
