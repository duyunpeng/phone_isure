package phoneisure.domain.model.moneydetailed;

import phoneisure.core.enums.FlowType;
import phoneisure.core.id.ConcurrencySafeEntity;
import phoneisure.domain.model.merchant.Merchant;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by YJH on 2016/4/21.
 */
public class MoneyDetailed extends ConcurrencySafeEntity {

    private Merchant merchant;      //商户
    private FlowType flowType;      //流向类型
    private BigDecimal money;       //金额
    private String explain;         //说明
    private String insuredName;     //参保人姓名

    private void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    private void setFlowType(FlowType flowType) {
        this.flowType = flowType;
    }

    private void setMoney(BigDecimal money) {
        this.money = money;
    }

    private void setExplain(String explain) {
        this.explain = explain;
    }

    private void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public FlowType getFlowType() {
        return flowType;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public String getExplain() {
        return explain;
    }

    public String getInsuredName() {
        return insuredName;
    }

    public MoneyDetailed() {
        super();
    }

    public MoneyDetailed(Merchant merchant, FlowType flowType, BigDecimal money, String explain, String insuredName) {
        this.merchant = merchant;
        this.flowType = flowType;
        this.money = money;
        this.explain = explain;
        this.insuredName = insuredName;
        this.setCreateDate(new Date());
    }
}
