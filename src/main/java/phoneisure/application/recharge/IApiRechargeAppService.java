package phoneisure.application.recharge;

import phoneisure.application.recharge.command.RechargeCommand;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.core.api.ApiResponse;
import phoneisure.core.pay.wechat.WechatNotify;

/**
 * Created by YJH on 2016/5/4.
 */
public interface IApiRechargeAppService {
    ApiResponse recharge(RechargeCommand command);

    void wechatSuccess(WechatNotify notify);

    ApiResponse searchByNo(SharedCommand command);
}
