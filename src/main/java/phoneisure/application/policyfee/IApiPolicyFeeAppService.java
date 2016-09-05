package phoneisure.application.policyfee;

import phoneisure.application.policyfee.command.ListPolicyFeeCommand;
import phoneisure.core.api.ApiResponse;

/**
 * Created by YJH on 2016/4/25.
 */
public interface IApiPolicyFeeAppService {
    ApiResponse list(ListPolicyFeeCommand command);
}
