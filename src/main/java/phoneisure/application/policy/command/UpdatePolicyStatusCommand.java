package phoneisure.application.policy.command;

import phoneisure.application.shared.command.SharedCommand;
import phoneisure.core.enums.PolicyStatus;

/**
 * Created by YJH on 2016/4/27.
 */
public class UpdatePolicyStatusCommand extends SharedCommand {
    private PolicyStatus policyStatus;

    public PolicyStatus getPolicyStatus() {
        return policyStatus;
    }

    public void setPolicyStatus(PolicyStatus policyStatus) {
        this.policyStatus = policyStatus;
    }
}
