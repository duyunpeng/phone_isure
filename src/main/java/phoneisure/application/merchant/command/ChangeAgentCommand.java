package phoneisure.application.merchant.command;

/**
 * Created by YJH on 2016/4/27.
 */
public class ChangeAgentCommand {

    private String merchant;
    private String agent;

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }
}
