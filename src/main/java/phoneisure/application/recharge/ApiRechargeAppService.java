package phoneisure.application.recharge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import phoneisure.application.recharge.command.RechargeCommand;
import phoneisure.application.recharge.representation.RechargeRepresentation;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.core.api.ApiResponse;
import phoneisure.core.api.ApiReturnCode;
import phoneisure.core.mapping.IMappingService;
import phoneisure.core.pay.wechat.UnifiedResponse;
import phoneisure.core.pay.wechat.WechatNotify;
import phoneisure.core.util.CoreStringUtils;
import phoneisure.domain.model.recharge.Recharge;
import phoneisure.domain.service.recharge.IRechargeService;

/**
 * Created by YJH on 2016/5/4.
 */
@Service("apiRechargeAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ApiRechargeAppService implements IApiRechargeAppService {

    @Autowired
    private IMappingService mappingService;

    @Autowired
    private IRechargeService rechargeService;

    @Override
    public ApiResponse recharge(RechargeCommand command) {
        if (CoreStringUtils.isEmpty(command.getMerchant())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "merchant字段不能为空", null);
        }
        if (null == command.getMoney()) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "money字段不能为空", null);
        }
        if (CoreStringUtils.isEmpty(command.getIpAddress())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "ipAddress字段不能为空", null);
        }
        RechargeRepresentation data = mappingService.map(rechargeService.apiRecharge(command), RechargeRepresentation.class, false);
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(), data);
    }

    @Override
    public void wechatSuccess(WechatNotify notify) {
        rechargeService.apiWechatSuccess(notify);
    }

    @Override
    public ApiResponse searchByNo(SharedCommand command) {
        if (CoreStringUtils.isEmpty(command.getId())) {
            return new ApiResponse(ApiReturnCode.ILLEGAL_ARGUMENT, "id字段不能为空", null);
        }
        RechargeRepresentation data = mappingService.map(rechargeService.searchByNo(command.getId()), RechargeRepresentation.class, false);
        return new ApiResponse(ApiReturnCode.SUCCESS, ApiReturnCode.SUCCESS.getName(), data);
    }

}
