package phoneisure.interfaces.merchant.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import phoneisure.application.merchant.IApiMerchantAppService;
import phoneisure.application.merchant.command.ListMerchantCommand;
import phoneisure.application.merchant.command.RegisterMerchantCommand;
import phoneisure.application.merchant.command.UpdatePasswordCommand;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.core.api.ApiResponse;
import phoneisure.core.api.ApiReturnCode;
import phoneisure.core.api.command.ApiVerificationCommand;
import phoneisure.core.api.controller.BaseApiController;
import phoneisure.core.exception.*;

/**
 * Created by YJH on 2016/4/23.
 */
@Controller
@RequestMapping("/merchant/api")
public class ApiMerchantController extends BaseApiController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiMerchantAppService apiMerchantAppService;

    @RequestMapping(value = "/register")
    @ResponseBody
    public ApiResponse register(ApiVerificationCommand verificationCommand) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            RegisterMerchantCommand command = this.authenticationAndConvert(verificationCommand, RegisterMerchantCommand.class);
            apiResponse = apiMerchantAppService.register(command);
        } catch (ExistException e) {
            logger.warn(e.getMessage());
            if (e.getMessage().indexOf("Account") != -1) {
                apiResponse = new ApiResponse(ApiReturnCode.ERROR_ACCOUNT_EXIST);
            } else {
                apiResponse = new ApiResponse(ApiReturnCode.ERROR_AGENT_EXIST);
            }
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            if (e.getMessage().indexOf("Agent") != -1) {
                apiResponse = new ApiResponse(ApiReturnCode.ERROR_AGENT_NO_FOUND);
            } else {
                apiResponse = new ApiResponse(ApiReturnCode.ERROR_DATA_NOT_FOUND);
            }
        } catch (ApiUnknownException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (Exception e) {
            logger.error(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.FAILURE);
        }
        apiResponse.setDebugTime(System.currentTimeMillis() - startTime);
        return apiResponse;
    }

    @RequestMapping(value = "/search_agent")
    @ResponseBody
    public ApiResponse searchAgent(ApiVerificationCommand verificationCommand) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            ListMerchantCommand command = this.authenticationAndConvert(verificationCommand, ListMerchantCommand.class);
            apiResponse = apiMerchantAppService.searchAgent(command);
        } catch (ApiUnknownException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (Exception e) {
            logger.error(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_UNKNOWN);
        }
        apiResponse.setDebugTime(System.currentTimeMillis() - startTime);
        return apiResponse;
    }

    @RequestMapping(value = "/update_password")
    @ResponseBody
    public ApiResponse updatePassword(ApiVerificationCommand verificationCommand) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            UpdatePasswordCommand command = this.authenticationAndConvert(verificationCommand, UpdatePasswordCommand.class);
            apiResponse = apiMerchantAppService.updatePassword(command);
        } catch (ApiUnknownException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (PasswordNotEquals e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_PASSWORD_NOT_EQ);
        } catch (Exception e) {
            logger.error(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_UNKNOWN);
        }
        apiResponse.setDebugTime(System.currentTimeMillis() - startTime);
        return apiResponse;
    }

    @RequestMapping(value = "/search_by_id")
    @ResponseBody
    public ApiResponse searchByID(ApiVerificationCommand verificationCommand) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            SharedCommand command = this.authenticationAndConvert(verificationCommand, SharedCommand.class);
            apiResponse = apiMerchantAppService.searchByID(command);
        } catch (ApiUnknownException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_DATA_NOT_FOUND);
        } catch (Exception e) {
            logger.error(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_UNKNOWN);
        }
        apiResponse.setDebugTime(System.currentTimeMillis() - startTime);
        return apiResponse;
    }
}
