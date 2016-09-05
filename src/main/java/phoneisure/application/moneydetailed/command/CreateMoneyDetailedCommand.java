package phoneisure.application.moneydetailed.command;

import phoneisure.core.enums.FlowType;

import java.math.BigDecimal;

/**
 * Created by YJH on 2016/4/27.
 */
public class CreateMoneyDetailedCommand {

    private String merchant;
    private BigDecimal money;
    private FlowType flowType;
    private String explain;
    private String insuredName;

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public FlowType getFlowType() {
        return flowType;
    }

    public void setFlowType(FlowType flowType) {
        this.flowType = flowType;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }
}
