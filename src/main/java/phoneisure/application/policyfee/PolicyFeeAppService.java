package phoneisure.application.policyfee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import phoneisure.application.policyfee.command.CreatePolicyFeeCommand;
import phoneisure.application.policyfee.command.EditPolicyFeeCommand;
import phoneisure.application.policyfee.command.ListPolicyFeeCommand;
import phoneisure.application.policyfee.representation.PolicyFeeRepresentation;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.core.mapping.IMappingService;
import phoneisure.domain.model.policyfee.PolicyFee;
import phoneisure.domain.service.policyfee.IPolicyFeeService;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by LvDi on 2016/4/23.
 */
@Service("policyFeeAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class PolicyFeeAppService implements IPolicyFeeAppService{
    @Autowired
    private IPolicyFeeService policyFeeService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public Pagination<PolicyFeeRepresentation> pagination(ListPolicyFeeCommand command) {
        command.verifyPage();
        command.verifyPageSize(30);
        Pagination<PolicyFee> pagination=policyFeeService.pagination(command);
        List<PolicyFeeRepresentation> data=mappingService.mapAsList(pagination.getData(),PolicyFeeRepresentation.class);
        return new Pagination<PolicyFeeRepresentation>(data,pagination.getCount(),pagination.getPage(),pagination.getPageSize());
    }

    @Override
    public PolicyFeeRepresentation searchByID(String id) {
        return mappingService.map(policyFeeService.searchByID(id),PolicyFeeRepresentation.class,false);
    }

    @Override
    public PolicyFeeRepresentation create(CreatePolicyFeeCommand command) {
        return mappingService.map(policyFeeService.create(command),PolicyFeeRepresentation.class,false);
    }

    @Override
    public PolicyFeeRepresentation edit(EditPolicyFeeCommand command) {
        return mappingService.map(policyFeeService.edit(command),PolicyFeeRepresentation.class,false);
    }

    @Override
    public void updateStatus(SharedCommand command) {
        policyFeeService.updateStatus(command);
    }
}
