package phoneisure.domain.service.moneydetailed;

import phoneisure.application.moneydetailed.command.CreateMoneyDetailedCommand;
import phoneisure.application.moneydetailed.command.ListMoneyDetailedCommand;
import phoneisure.domain.model.moneydetailed.MoneyDetailed;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by dyp on 16-4-22.
 */
public interface IMoneyDetailedService {

    Pagination<MoneyDetailed> pagination(ListMoneyDetailedCommand command);

    List<MoneyDetailed> list(ListMoneyDetailedCommand command);

    void create(CreateMoneyDetailedCommand moneyDetailedCommand);

    BigDecimal count(ListMoneyDetailedCommand command);

    BigDecimal userMoney(ListMoneyDetailedCommand command);

}
