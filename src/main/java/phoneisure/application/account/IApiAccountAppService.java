package phoneisure.application.account;

import phoneisure.application.account.command.ResetPasswordCommand;
import phoneisure.application.account.command.SearchAccountCommand;
import phoneisure.application.account.command.UpdateLoginCommand;
import phoneisure.core.api.ApiResponse;

/**
 * Created by YJH on 2016/4/25.
 */
public interface IApiAccountAppService {
    ApiResponse searchByName(SearchAccountCommand command);

    ApiResponse updateLogin(UpdateLoginCommand command);

    ApiResponse resetPassword(ResetPasswordCommand command);
}
