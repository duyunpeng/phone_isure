package phoneisure.interfaces.account.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import phoneisure.application.account.IApiAccountAppService;
import phoneisure.application.account.command.ResetPasswordCommand;
import phoneisure.application.account.command.SearchAccountCommand;
import phoneisure.application.account.command.UpdateLoginCommand;
import phoneisure.application.auth.command.LoginCommand;
import phoneisure.core.api.ApiResponse;
import phoneisure.core.api.ApiReturnCode;
import phoneisure.core.api.command.ApiVerificationCommand;
import phoneisure.core.api.controller.BaseApiController;
import phoneisure.core.exception.ApiAuthenticationException;
import phoneisure.core.exception.ApiUnknownException;
import phoneisure.core.exception.NoFoundException;

/**
 * Created by YJH on 2016/4/25.
 */
@Controller
@RequestMapping("/account/api")
public class ApiAccountController extends BaseApiController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiAccountAppService apiAccountAppService;

    @RequestMapping(value = "/search_by_name")
    @ResponseBody
    public ApiResponse searchByName(ApiVerificationCommand verificationCommand) {
        long startTime = System.currentTimeMillis();
        ApiResponse response;
        try {
            SearchAccountCommand command = this.authenticationAndConvert(verificationCommand, SearchAccountCommand.class);
            response = apiAccountAppService.searchByName(command);
        } catch (ApiUnknownException e) {
            logger.warn(e.getMessage());
            response = e.getResponse();
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            response = e.getResponse();
        }
        response.setDebugTime(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/update_login")
    @ResponseBody
    public ApiResponse updateLogin(ApiVerificationCommand verificationCommand) {
        long startTime = System.currentTimeMillis();
        ApiResponse response;
        try {
            UpdateLoginCommand command = this.authenticationAndConvert(verificationCommand, UpdateLoginCommand.class);
            response = apiAccountAppService.updateLogin(command);
        } catch (ApiUnknownException e) {
            logger.warn(e.getMessage());
            response = e.getResponse();
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            response = e.getResponse();
        }
        response.setDebugTime(System.currentTimeMillis() - startTime);
        return response;
    }

    @RequestMapping(value = "/reset_password")
    @ResponseBody
    public ApiResponse resetPassword(ApiVerificationCommand verificationCommand) {
        long startTime = System.currentTimeMillis();
        ApiResponse response;
        try {
            ResetPasswordCommand command = this.authenticationAndConvert(verificationCommand, ResetPasswordCommand.class);
            response = apiAccountAppService.resetPassword(command);
        } catch (ApiUnknownException e) {
            logger.warn(e.getMessage());
            response = e.getResponse();
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            response = e.getResponse();
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            response = new ApiResponse(ApiReturnCode.ERROR_DATA_NOT_FOUND);
        }
        response.setDebugTime(System.currentTimeMillis() - startTime);
        return response;
    }

}
