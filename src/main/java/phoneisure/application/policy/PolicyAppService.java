package phoneisure.application.policy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import phoneisure.application.policy.command.HandlePolicyCommand;
import phoneisure.application.policy.command.ListPolicyCommand;
import phoneisure.application.policy.command.UpdatePolicyPicCommand;
import phoneisure.application.policy.command.UpdatePolicyStatusCommand;
import phoneisure.application.policy.representation.PolicyCountRepresentation;
import phoneisure.application.policy.representation.PolicyRepresentation;
import phoneisure.core.enums.PolicyStatus;
import phoneisure.core.mapping.IMappingService;
import phoneisure.domain.model.policy.Policy;
import phoneisure.domain.service.policy.IPolicyService;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LvDi on 2016/4/22.
 */
@Service("policyAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class PolicyAppService implements IPolicyAppService {

    @Autowired
    private IPolicyService policyService;

    @Autowired
    private IMappingService mappingService;

    @Override
    public Pagination<PolicyRepresentation> pagination(ListPolicyCommand command) {
        command.verifyPage();
        command.verifyPageSize(30);
        Pagination<Policy> pagination = policyService.pagination(command);
        List<PolicyRepresentation> data = mappingService.mapAsList(pagination.getData(), PolicyRepresentation.class);
        return new Pagination<PolicyRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public Pagination<PolicyRepresentation> chargeBackPagination(ListPolicyCommand command) {
        command.verifyPage();
        command.verifyPageSize(30);
        Pagination<Policy> pagination = policyService.chargeBackPagination(command);
        List<PolicyRepresentation> data = mappingService.mapAsList(pagination.getData(), PolicyRepresentation.class);
        return new Pagination<PolicyRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public Pagination<PolicyRepresentation> claimForCompensationPagination(ListPolicyCommand command) {
        command.verifyPage();
        command.verifyPageSize(30);
        Pagination<Policy> pagination = policyService.claimForCompensationPagination(command);
        List<PolicyRepresentation> data = mappingService.mapAsList(pagination.getData(), PolicyRepresentation.class);
        return new Pagination<PolicyRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }


    @Override
    public Pagination<PolicyRepresentation> agentPagination(ListPolicyCommand command) {
        command.verifyPage();
        command.verifyPageSize(30);
        Pagination<Policy> pagination = policyService.paginationByAgent(command);
        List<PolicyRepresentation> data = mappingService.mapAsList(pagination.getData(), PolicyRepresentation.class);
        return new Pagination<PolicyRepresentation>(data, pagination.getCount(), pagination.getPage(), pagination.getPageSize());
    }

    @Override
    public void updatePolicyStatus(UpdatePolicyStatusCommand command) {
        policyService.updatePolicyStatus(command);
    }

    @Override
    public void handlePolicy(HandlePolicyCommand command) {
        policyService.handlePolicy(command);
    }

    @Override
    public void successClaim(String id) {
        policyService.successClaim(id);
    }

    @Override
    public PolicyRepresentation searchByID(String id) {
        return mappingService.map(policyService.searchByID(id), PolicyRepresentation.class, false);
    }

    @Override
    public PolicyCountRepresentation policyCount(String id) {
        List<String> ids = policyService.getChildrenId(new ArrayList<String>(), id);
        Integer all = policyService.policyCount(ids, PolicyStatus.ALL).getCount();
        Integer back = policyService.policyCount(ids, PolicyStatus.SUCCESS_BACK).getCount();
        Integer claim = policyService.policyCount(ids, PolicyStatus.SUCCESS_CLAIM).getCount();
        return new PolicyCountRepresentation(all, back, claim);
    }

    @Override
    public List<PolicyRepresentation> exportExcel(ListPolicyCommand command) {
        return mappingService.mapAsList(policyService.exportExcel(command), PolicyRepresentation.class);
    }

    @Override
    public void adminReturnPolicy(UpdatePolicyStatusCommand command) {
        policyService.adminReturnPolicy(command);
    }

    @Override
    public void updatePic(UpdatePolicyPicCommand command) {
        policyService.updatePic(command);
    }

}
