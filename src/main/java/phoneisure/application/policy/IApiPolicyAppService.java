package phoneisure.application.policy;

import phoneisure.application.policy.command.CreatePolicyCommand;
import phoneisure.application.policy.command.ListPolicyCommand;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.core.api.ApiResponse;

/**
 * Created by YJH on 2016/4/25.
 */
public interface IApiPolicyAppService {
    ApiResponse create(CreatePolicyCommand command);

    ApiResponse list(ListPolicyCommand command);

    ApiResponse returnPolicy(SharedCommand command);

    ApiResponse policyCount(SharedCommand command);
}
