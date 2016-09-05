package phoneisure.application.moneydetailed;

import phoneisure.application.moneydetailed.command.ListMoneyDetailedCommand;
import phoneisure.application.moneydetailed.representation.MoneyDetailedRepresentation;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by dyp on 16-4-22.
 */
public interface IMoneyDetailedAppService {

    Pagination<MoneyDetailedRepresentation> pagination(ListMoneyDetailedCommand command);

    BigDecimal count(ListMoneyDetailedCommand command);

    List<MoneyDetailedRepresentation> listJSON(ListMoneyDetailedCommand command);

    BigDecimal userMoney(ListMoneyDetailedCommand command);

}
