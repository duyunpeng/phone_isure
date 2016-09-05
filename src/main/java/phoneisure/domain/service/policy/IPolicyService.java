package phoneisure.domain.service.policy;

import phoneisure.application.policy.command.*;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.core.enums.PolicyStatus;
import phoneisure.domain.model.policy.Policy;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by LvDi on 2016/4/22.
 */
public interface IPolicyService {

    Pagination<Policy> pagination(ListPolicyCommand command);

    Pagination<Policy> chargeBackPagination(ListPolicyCommand command);

    Pagination<Policy> claimForCompensationPagination(ListPolicyCommand command);

    Pagination<Policy> paginationByAgent(ListPolicyCommand command);

    Policy searchByID(String id);

    Pagination<Policy> policyCount(List<String> id, PolicyStatus policyStatus);

    List<String> getChildrenId(List<String> ids, String id);

    void updatePolicyStatus(UpdatePolicyStatusCommand command);

    void handlePolicy(HandlePolicyCommand command);

    void successClaim(String id);

    List<Policy> exportExcel(ListPolicyCommand command);

    List<Policy> searchByIMEI(String imei);

    void adminReturnPolicy(UpdatePolicyStatusCommand command);

    void updatePic(UpdatePolicyPicCommand command);

    //api
    void apiCreate(CreatePolicyCommand command);

    Pagination<Policy> apiPagination(ListPolicyCommand command);


    void apiReturnPolicy(SharedCommand command);

    Pagination<Policy> apiPolicyCount(SharedCommand command, PolicyStatus policyStatus);
}
