package phoneisure.application.policy;

import phoneisure.application.policy.command.HandlePolicyCommand;
import phoneisure.application.policy.command.ListPolicyCommand;
import phoneisure.application.policy.command.UpdatePolicyPicCommand;
import phoneisure.application.policy.command.UpdatePolicyStatusCommand;
import phoneisure.application.policy.representation.PolicyCountRepresentation;
import phoneisure.application.policy.representation.PolicyRepresentation;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by LvDi on 2016/4/22.
 */
public interface IPolicyAppService {
    Pagination<PolicyRepresentation> pagination(ListPolicyCommand command);

    Pagination<PolicyRepresentation> chargeBackPagination(ListPolicyCommand command);

    Pagination<PolicyRepresentation> claimForCompensationPagination(ListPolicyCommand command);

    Pagination<PolicyRepresentation> agentPagination(ListPolicyCommand command);

    PolicyRepresentation searchByID(String id);

    void updatePolicyStatus(UpdatePolicyStatusCommand command);

    void handlePolicy(HandlePolicyCommand command);

    void successClaim(String id);

    PolicyCountRepresentation policyCount(String id);

    List<PolicyRepresentation> exportExcel(ListPolicyCommand command);

    void adminReturnPolicy(UpdatePolicyStatusCommand command);

    void updatePic(UpdatePolicyPicCommand command);
}
