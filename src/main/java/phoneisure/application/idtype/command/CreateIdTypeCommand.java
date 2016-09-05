package phoneisure.application.idtype.command;
import org.hibernate.validator.constraints.NotBlank;
import phoneisure.core.enums.EnableStatus;

import javax.validation.constraints.NotNull;


/**
 * Created by Administrator on 2016/4/23.
 */
public class CreateIdTypeCommand {
    @NotBlank(message = "{idType.name.NotBlank.message}")
    private String name;            //证件名称
    @NotNull(message = "{idType.status.NotNull.messages}")
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
