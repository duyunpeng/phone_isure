package phoneisure.application.area.command;


import phoneisure.core.commons.BasicPaginationCommand;
import phoneisure.core.enums.AreaLevel;
import phoneisure.core.enums.EnableStatus;

/**
 * Created by YJH on 2016/4/14.
 */
public class ListAreaCommand extends BasicPaginationCommand {

    private String name;
    private AreaLevel level;
    private EnableStatus status;
    private String parent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AreaLevel getLevel() {
        return level;
    }

    public void setLevel(AreaLevel level) {
        this.level = level;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}
