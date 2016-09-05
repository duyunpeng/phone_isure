package phoneisure.application.policyfee;

import phoneisure.application.policyfee.command.CreatePolicyFeeCommand;
import phoneisure.application.policyfee.command.EditPolicyFeeCommand;
import phoneisure.application.policyfee.command.ListPolicyFeeCommand;
import phoneisure.application.policyfee.representation.PolicyFeeRepresentation;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

/**
 * Created by LvDi on 2016/4/23.
 */
public interface IPolicyFeeAppService {

    Pagination<PolicyFeeRepresentation> pagination(ListPolicyFeeCommand command);

    PolicyFeeRepresentation searchByID(String id);

    PolicyFeeRepresentation create(CreatePolicyFeeCommand command);

    PolicyFeeRepresentation edit(EditPolicyFeeCommand command);

    void updateStatus(SharedCommand command);
}
