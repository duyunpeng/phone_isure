package phoneisure.domain.service.recharge;

import phoneisure.application.recharge.command.ListRechargeCommand;
import phoneisure.application.recharge.command.RechargeCommand;
import phoneisure.core.pay.wechat.UnifiedResponse;
import phoneisure.core.pay.wechat.WechatNotify;
import phoneisure.domain.model.recharge.Recharge;
import phoneisure.infrastructure.persistence.hibernate.generic.Pagination;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by dyp on 2016/4/22.
 */
public interface IRechargeService {

    Pagination<Recharge> pagination(ListRechargeCommand command);

    List<Recharge> list(ListRechargeCommand command);

    Recharge apiRecharge(RechargeCommand command);

    void apiWechatSuccess(WechatNotify notify);

    Recharge searchByNo(String rechargeNo);

    BigDecimal count(ListRechargeCommand command);
}
