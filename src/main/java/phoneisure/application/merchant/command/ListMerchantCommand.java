package phoneisure.application.merchant.command;

import phoneisure.core.commons.BasicPaginationCommand;
import phoneisure.core.enums.EnableStatus;
import phoneisure.core.enums.UserType;
import phoneisure.core.enums.YesOrNoStatus;

/**
 * Created by YJH on 2016/4/22.
 */
public class ListMerchantCommand extends BasicPaginationCommand {

    private String userName;

    private String merchantName;    //商户名称
    private String appKey;
    private EnableStatus status;
    private UserType userType;          //用户类型
    private String agent;               //代理
    private YesOrNoStatus isRecharge;   //是否充值

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public YesOrNoStatus getIsRecharge() {
        return isRecharge;
    }

    public void setIsRecharge(YesOrNoStatus isRecharge) {
        this.isRecharge = isRecharge;
    }
}
