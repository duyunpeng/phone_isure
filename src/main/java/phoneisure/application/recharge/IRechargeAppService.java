package phoneisure.application.recharge;

import phoneisure.application.recharge.command.ListRechargeCommand;
import phoneisure.application.recharge.representation.RechargeRepresentation;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by dyp on 2016/4/22.
 */
public interface IRechargeAppService {

    Pagination<RechargeRepresentation> pagination(ListRechargeCommand command);

    BigDecimal count(ListRechargeCommand command);

    List<RechargeRepresentation> listJSON(ListRechargeCommand command);
}
