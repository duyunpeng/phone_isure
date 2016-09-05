package phoneisure.application.policyfee.command;

import phoneisure.core.commons.BasicPaginationCommand;
import phoneisure.core.enums.EnableStatus;

/**
 * Created by LvDi on 2016/4/23.
 */
public class ListPolicyFeeCommand extends BasicPaginationCommand {

    private String phoneBrand;      //手机品牌
    private String phoneModel;          //手机型号
    private EnableStatus status;        //状态

    public EnableStatus getStatus() {
        return status;
    }

    public void setStatus(EnableStatus status) {
        this.status = status;
    }

    public String getPhoneBrand() {
        return phoneBrand;
    }

    public void setPhoneBrand(String phoneBrand) {
        this.phoneBrand = phoneBrand;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }
}
