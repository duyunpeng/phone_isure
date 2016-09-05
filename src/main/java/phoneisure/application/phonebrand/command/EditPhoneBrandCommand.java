package phoneisure.application.phonebrand.command;

import org.hibernate.validator.constraints.NotBlank;
import phoneisure.application.shared.command.SharedCommand;
import phoneisure.core.enums.EnableStatus;

import javax.validation.constraints.NotNull;

/**
 * Created by Administrator on 2016/4/22.
 */
public class EditPhoneBrandCommand extends SharedCommand {

    @NotBlank(message = "{phoneBrand.name.NotBlank.message}")
    private String name;            //名称

    @NotNull(message = "{phoneBrand.sort.NotNull.message}")
    private Integer sort;           //排序

    @NotNull(message = "{phoneBrand.status.NotNull.message}")
    private EnableStatus status;    //状态

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }
}
