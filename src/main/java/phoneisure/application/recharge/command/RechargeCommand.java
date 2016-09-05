package phoneisure.application.recharge.command;

import java.math.BigDecimal;

/**
 * Created by YJH on 2016/5/4.
 */
public class RechargeCommand {

    private String merchant;
    private BigDecimal money;
    private String ipAddress;

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

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
