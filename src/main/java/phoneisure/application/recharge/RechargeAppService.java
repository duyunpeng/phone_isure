package phoneisure.application.recharge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import phoneisure.application.recharge.command.ListRechargeCommand;
import phoneisure.application.recharge.representation.RechargeRepresentation;
import phoneisure.core.mapping.IMappingService;
import phoneisure.domain.model.recharge.Recharge;
import phoneisure.domain.service.recharge.IRechargeService;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by dyp on 2016/4/22.
 */
@Service("rechargeAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class RechargeAppService implements IRechargeAppService{

    @Autowired
    private IRechargeService rechargeService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public Pagination<RechargeRepresentation> pagination(ListRechargeCommand command) {
        command.verifyPage();
        command.verifyPageSize(30);
        Pagination<Recharge> pagination = rechargeService.pagination(command);
        List<RechargeRepresentation> data = mappingService.mapAsList(pagination.getData(),RechargeRepresentation.class);
        return new Pagination<RechargeRepresentation>(data,pagination.getCount(),pagination.getPage(),pagination.getPageSize());
    }

    @Override
    public BigDecimal count(ListRechargeCommand command) {
        return rechargeService.count(command);
    }

    @Override
    public List<RechargeRepresentation> listJSON(ListRechargeCommand command) {
        return mappingService.mapAsList(rechargeService.list(command),RechargeRepresentation.class);
    }
}
