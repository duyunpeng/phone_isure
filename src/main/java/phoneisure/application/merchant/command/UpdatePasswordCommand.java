package phoneisure.application.merchant.command;

/**
 * Created by YJH on 2016/4/26.
 */
public class UpdatePasswordCommand {

    private String merchant;
    private String oldPassword;
    private String newPassword;

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
