package phoneisure.application.account.command;

import phoneisure.application.shared.command.SharedCommand;

/**
 * Created by YJH on 2016/3/30 0030.
 */
public class EditAccountCommand extends SharedCommand {

    private String email;   //邮箱

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
