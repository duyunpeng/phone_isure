package phoneisure.interfaces.policyfee.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import phoneisure.application.policyfee.IApiPolicyFeeAppService;
import phoneisure.application.policyfee.command.ListPolicyFeeCommand;
import phoneisure.core.api.ApiResponse;
import phoneisure.core.api.ApiReturnCode;
import phoneisure.core.api.command.ApiVerificationCommand;
import phoneisure.core.api.controller.BaseApiController;
import phoneisure.core.exception.ApiAuthenticationException;

import java.util.List;

/**
 * Created by YJH on 2016/4/25.
 */
@Controller
@RequestMapping("/policy_fee/api")
public class ApiPolicyFeeController extends BaseApiController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiPolicyFeeAppService apiPolicyFeeAppService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public ApiResponse list(ApiVerificationCommand verificationCommand) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            ListPolicyFeeCommand command = authenticationAndConvert(verificationCommand,ListPolicyFeeCommand.class);
            apiResponse = apiPolicyFeeAppService.list(command);
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
}
