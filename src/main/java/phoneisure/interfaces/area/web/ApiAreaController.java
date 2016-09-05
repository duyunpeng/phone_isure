package phoneisure.interfaces.area.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import phoneisure.application.area.IApiAreaAppService;
import phoneisure.application.area.command.ListAreaCommand;
import phoneisure.core.api.ApiResponse;
import phoneisure.core.api.command.ApiVerificationCommand;
import phoneisure.core.api.controller.BaseApiController;
import phoneisure.core.exception.ApiAuthenticationException;
import phoneisure.core.exception.ApiUnknownException;

/**
 * Created by YJH on 2016/4/23.
 */
@Controller
@RequestMapping("/area/api")
public class ApiAreaController extends BaseApiController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiAreaAppService apiAreaAppService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public ApiResponse list(ApiVerificationCommand verificationCommand) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            ListAreaCommand command = this.authenticationAndConvert(verificationCommand, ListAreaCommand.class);
            apiResponse = apiAreaAppService.searchByParent(command);
        } catch (ApiUnknownException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        }
        apiResponse.setDebugTime(System.currentTimeMillis() - startTime);
        return apiResponse;
    }

}
