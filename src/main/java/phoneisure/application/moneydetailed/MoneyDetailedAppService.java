package phoneisure.application.moneydetailed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import phoneisure.application.moneydetailed.command.ListMoneyDetailedCommand;
import phoneisure.application.moneydetailed.representation.MoneyDetailedRepresentation;
import phoneisure.core.mapping.IMappingService;
import phoneisure.domain.model.moneydetailed.MoneyDetailed;
import phoneisure.domain.service.moneydetailed.IMoneyDetailedService;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by dyp on 16-4-22.
 */
@Service("moneyDetailedAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class MoneyDetailedAppService implements IMoneyDetailedAppService {

    @Autowired
    private IMoneyDetailedService moneyDetailedService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public Pagination<MoneyDetailedRepresentation> pagination(ListMoneyDetailedCommand command) {
        command.verifyPage();
        command.verifyPageSize(30);
        Pagination<MoneyDetailed> pagination = moneyDetailedService.pagination(command);
        List<MoneyDetailedRepresentation> data = mappingService.mapAsList(pagination.getData(), MoneyDetailedRepresentation.class);
        return new Pagination<MoneyDetailedRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public BigDecimal count(ListMoneyDetailedCommand command) {
        return moneyDetailedService.count(command);
    }

    @Override
    public List<MoneyDetailedRepresentation> listJSON(ListMoneyDetailedCommand command) {
        return mappingService.mapAsList(moneyDetailedService.list(command), MoneyDetailedRepresentation.class);
    }

    @Override
    public BigDecimal userMoney(ListMoneyDetailedCommand command) {
        return moneyDetailedService.userMoney(command);
    }
}
