package phoneisure.interfaces.recharge.web;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import phoneisure.application.recharge.IApiRechargeAppService;
import phoneisure.application.recharge.command.RechargeCommand;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.core.api.ApiResponse;
import phoneisure.core.api.ApiReturnCode;
import phoneisure.core.api.command.ApiVerificationCommand;
import phoneisure.core.api.controller.BaseApiController;
import phoneisure.core.enums.PayType;
import phoneisure.core.exception.ApiAuthenticationException;
import phoneisure.core.exception.ApiUnknownException;
import phoneisure.core.pay.wechat.WechatNotify;
import phoneisure.core.pay.wechat.util.Signature;
import phoneisure.core.pay.wechat.util.XMLParser;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by YJH on 2016/5/4.
 */
@Controller
@RequestMapping("/recharge/api")
public class ApiRechargeController extends BaseApiController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IApiRechargeAppService apiRechargeAppService;

    @RequestMapping(value = "/recharge")
    @ResponseBody
    public ApiResponse recharge(ApiVerificationCommand verificationCommand) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            RechargeCommand command = this.authenticationAndConvert(verificationCommand, RechargeCommand.class);
            apiResponse = apiRechargeAppService.recharge(command);
        } catch (ApiUnknownException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (ApiAuthenticationException e) {
            logger.warn(e.getMessage());
            apiResponse = e.getResponse();
        } catch (Exception e) {
            logger.warn(e.getMessage());
            apiResponse = new ApiResponse(ApiReturnCode.ERROR_UNKNOWN);
        }
        apiResponse.setDebugTime(System.currentTimeMillis() - startTime);
        return apiResponse;
    }

    @RequestMapping(value = "/wechat/notify")
    public String rechargeWecatNotify(HttpServletRequest request, Locale locale) {
        int contentLength = request.getContentLength();
        byte[] bytes = new byte[contentLength];
        WechatNotify notify = null;
        try {
            request.getInputStream().read(bytes, 0, contentLength);
            notify = (WechatNotify) XMLParser.getObjFromXML(new String(bytes), WechatNotify.class);
            logger.info("response---------------->" + new String(bytes, "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String sign = null;
        if (notify != null) {
            sign = notify.getSign();
            notify.setSign("");
            try {
                String mySign = Signature.getWechatSign(notify);
                if (mySign.equals(sign)) {
                    if (notify.getReturn_code().equals("SUCCESS")) {
                        if (notify.getResult_code().equals("SUCCESS")) {
                            apiRechargeAppService.wechatSuccess(notify);
                            logger.info("充值流水号为[" + notify.getOut_trade_no() + "]的订单支付成功，支付方式为[" + PayType.WECHAT + "]");

                            return "<xml>\n" +
                                    "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                                    "  <return_msg><![CDATA[OK]]></return_msg>\n" +
                                    "</xml>";
                        } else {
                            logger.info("充值流水号为[" + notify.getOut_trade_no() + "]的订单支付失败，原因[" + notify.getErr_code_des() + "]");
                        }

                    } else {
                        logger.info("充值流水号为[" + notify.getOut_trade_no() + "]的订单支付失败，原因[" + notify.getReturn_msg() + "]");
                    }
                } else {
                    logger.info("充值流水号为[" + notify.getOut_trade_no() + "]的订单支付失败，原因[{1}]");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return "<xml>\n" +
                "  <return_code><![CDATA[FAIL]]></return_code>\n" +
                "  <return_msg><![CDATA[错误]]></return_msg>\n" +
                "</xml>";
    }

    @RequestMapping(value = "/search_by_no")
    @ResponseBody
    public ApiResponse searchByNo(ApiVerificationCommand verificationCommand) {
        long startTime = System.currentTimeMillis();
        ApiResponse apiResponse;
        try {
            SharedCommand command = this.authenticationAndConvert(verificationCommand, SharedCommand.class);
            apiResponse = apiRechargeAppService.searchByNo(command);
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
}
