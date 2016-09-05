package phoneisure.application.appkey.command;


import phoneisure.core.commons.BasicPaginationCommand;
import phoneisure.core.enums.EnableStatus;

/**
 * Created by YJH on 2016/3/30.
 */
public class ListAppKeyCommand extends BasicPaginationCommand {

    private String name;
    private EnableStatus status;

    private String appKeyName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }

    public String getAppKeyName() {
        return appKeyName;
    }

    public void setAppKeyName(String appKeyName) {
        this.appKeyName = appKeyName;
    }
}
