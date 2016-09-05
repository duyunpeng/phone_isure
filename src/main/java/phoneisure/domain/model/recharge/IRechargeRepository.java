package phoneisure.domain.model.recharge;

import phoneisure.application.recharge.command.ListRechargeCommand;
import phoneisure.core.enums.FlowType;
import phoneisure.core.enums.YesOrNoStatus;
import phoneisure.infrastructure.persistence.hibernate.generic.IHibernateGenericRepository;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by YJH on 2016/4/21.
 */
public interface IRechargeRepository<T, ID extends Serializable> extends IHibernateGenericRepository<T, ID> {
    Recharge searchByNo(String rechargeNo);

    BigDecimal count(ListRechargeCommand command, YesOrNoStatus isPay);
}
