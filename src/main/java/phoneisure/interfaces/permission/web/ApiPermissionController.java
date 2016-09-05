package phoneisure.interfaces.permission.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import phoneisure.application.permission.IApiPermissionAppService;
import phoneisure.application.permission.command.ListPermissionCommand;
import phoneisure.core.api.ApiResponse;
import phoneisure.core.api.ApiReturnCode;
import phoneisure.core.api.command.ApiVerificationCommand;
import phoneisure.core.api.controller.BaseApiController;
import phoneisure.core.exception.ApiAuthenticationException;
import phoneisure.core.exception.ApiUnknownException;

/**
 * Created by YJH on 2016/4/23.
 */
@Controller
@RequestMapping("/permission/api")
public class ApiPermissionController extends BaseApiController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiPermissionAppService apiPermissionAppService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public ApiResponse list(ApiVerificationCommand verificationCommand) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            ListPermissionCommand command = this.authenticationAndConvert(verificationCommand, ListPermissionCommand.class);
            apiResponse = apiPermissionAppService.apiList(command);
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
}
