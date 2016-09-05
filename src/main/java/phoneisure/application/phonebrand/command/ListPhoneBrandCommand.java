package phoneisure.application.phonebrand.command;

import phoneisure.core.commons.BasicPaginationCommand;
import phoneisure.core.enums.EnableStatus;

/**
 * Created by LvDI on 2016/4/22.
 */
public class ListPhoneBrandCommand extends BasicPaginationCommand {

    private String name;            //名称
    private EnableStatus status;    //状态

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
