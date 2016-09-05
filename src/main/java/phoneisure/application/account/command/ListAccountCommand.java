package phoneisure.application.account.command;


import phoneisure.core.commons.BasicPaginationCommand;
import phoneisure.core.enums.EnableStatus;

/**
 * Created by YJH on 2016/3/30 0030.
 */
public class ListAccountCommand extends BasicPaginationCommand {

    private String userName;
    private String appKey;
    private EnableStatus status;

    private String accountUserName;

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

    public String getAccountUserName() {
        return accountUserName;
    }

    public void setAccountUserName(String accountUserName) {
        this.accountUserName = accountUserName;
    }
}
