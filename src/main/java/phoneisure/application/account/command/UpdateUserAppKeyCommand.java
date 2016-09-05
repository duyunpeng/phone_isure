package phoneisure.application.account.command;


import org.hibernate.validator.constraints.NotBlank;
import phoneisure.application.shared.command.SharedCommand;

/**
 * Created by YJH on 2016/4/5.
 */
public class UpdateUserAppKeyCommand extends SharedCommand {

    @NotBlank(message = "{user.appKey.NotBlank.messages}")
    private String appKey;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}
