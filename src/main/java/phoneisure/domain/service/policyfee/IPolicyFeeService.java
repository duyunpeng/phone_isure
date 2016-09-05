package phoneisure.domain.service.policyfee;

import phoneisure.application.policyfee.command.CreatePolicyFeeCommand;
import phoneisure.application.policyfee.command.EditPolicyFeeCommand;
import phoneisure.application.policyfee.command.ListPolicyFeeCommand;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.domain.model.policyfee.PolicyFee;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.List;

/**
 * Created by LvDi on 2016/4/23.
 */
public interface IPolicyFeeService {

    Pagination<PolicyFee> pagination(ListPolicyFeeCommand command);

    PolicyFee searchByID(String id);

    PolicyFee create(CreatePolicyFeeCommand command);

    PolicyFee edit(EditPolicyFeeCommand command);

    void updateStatus(SharedCommand command);

    List<PolicyFee> list(ListPolicyFeeCommand command);
}
