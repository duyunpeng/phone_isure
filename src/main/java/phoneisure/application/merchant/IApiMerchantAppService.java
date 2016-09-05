package phoneisure.application.merchant;

import phoneisure.application.merchant.command.ListMerchantCommand;
import phoneisure.application.merchant.command.RegisterMerchantCommand;
import phoneisure.application.merchant.command.UpdatePasswordCommand;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.core.api.ApiResponse;

/**
 * Created by YJH on 2016/4/23.
 */
public interface IApiMerchantAppService {
    ApiResponse register(RegisterMerchantCommand command);

    ApiResponse searchAgent(ListMerchantCommand command);

    ApiResponse updatePassword(UpdatePasswordCommand command);

    ApiResponse searchByID(SharedCommand command);
}
