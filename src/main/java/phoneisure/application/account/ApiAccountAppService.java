package phoneisure.application.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import phoneisure.application.account.command.ResetPasswordCommand;
import phoneisure.application.account.command.SearchAccountCommand;
import phoneisure.application.account.command.UpdateLoginCommand;
import phoneisure.application.account.representation.AccountRepresentation;
import phoneisure.core.api.ApiResponse;
import phoneisure.core.api.ApiReturnCode;
import phoneisure.core.mapping.IMappingService;
import phoneisure.core.util.CoreStringUtils;
import phoneisure.domain.model.account.Account;
import phoneisure.domain.service.account.IAccountService;

/**
 * Created by YJH on 2016/4/25.
 */
@Service("apiAccountAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiAccountAppService implements IApiAccountAppService {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IMappingService mappingService;


    @Override
    @Transactional(readOnly = true)
    public ApiResponse searchByName(SearchAccountCommand command) {
        if (CoreStringUtils.isEmpty(command.getUserName())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "userName字段不能为空", null);
        }
        if (CoreStringUtils.isEmpty(command.getAppKey())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "appKey字段不能为空", null);
        }
        Account account = accountService.searchByAccountName(command.getUserName(), command.getAppKey());
        AccountRepresentation data = account != null ? mappingService.map(account, AccountRepresentation.class, false) : null;
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(), data);
    }

    @Override
    public ApiResponse updateLogin(UpdateLoginCommand command) {
        if (CoreStringUtils.isEmpty(command.getId())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "id字段不能为空", null);
        }
        if (CoreStringUtils.isEmpty(command.getAppKey())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "appKey字段不能为空", null);
        }
        if (CoreStringUtils.isEmpty(command.getLoginIP())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "loginIP字段不能为空", null);
        }
        if (CoreStringUtils.isEmpty(command.getLoginPlatform())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "loginPlatform字段不能为空", null);
        }
        Account account = accountService.apiUpdateLogin(command);
        AccountRepresentation data = mappingService.map(account, AccountRepresentation.class, false);
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(), data);
    }

    @Override
    public ApiResponse resetPassword(ResetPasswordCommand command) {
        if (CoreStringUtils.isEmpty(command.getUserName())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "userName字段不能为空", null);
        }
        if (CoreStringUtils.isEmpty(command.getPassword())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "password字段不能为空", null);
        }
        if(CoreStringUtils.isEmpty(command.getAppKey())){
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "appKey字段不能为空", null);
        }
        accountService.apiResetPassword(command);
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(), null);
    }
}
