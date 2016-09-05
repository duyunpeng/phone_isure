package phoneisure.application.idtype.command;

import phoneisure.core.commons.BasicPaginationCommand;
import phoneisure.core.enums.EnableStatus;

/**
 * Created by dyp on 2016/4/23.
 */
public class ListIdTypeCommand extends BasicPaginationCommand{
    private String name;            //证件名称
    private EnableStatus status;    //状态

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
}
