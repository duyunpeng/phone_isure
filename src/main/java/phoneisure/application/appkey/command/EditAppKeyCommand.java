package phoneisure.application.appkey.command;

import org.hibernate.validator.constraints.NotBlank;
import phoneisure.application.shared.command.SharedCommand;

/**
 * Created by YJH on 2016/3/30.
 */
public class EditAppKeyCommand extends SharedCommand {

    @NotBlank(message = "{appKey.name.NotBlank.Messages}")
    private String name;        //应用标识名称
    @NotBlank(message = "{appKey.description.NotBlank.Messages}")
    private String description;    //用用标识描述
    @NotBlank(message = "{appKey.projectName.NotBlank.Messages}")
    private String projectName; //应用标识项目名

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
