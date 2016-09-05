package phoneisure.application.policyfee.command;

import org.hibernate.validator.constraints.NotBlank;
import phoneisure.core.enums.EnableStatus;
import phoneisure.domain.model.phonebrand.PhoneBrand;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by LvDi on 2016/4/23.
 */
public class CreatePolicyFeeCommand {

    @NotNull(message = "{policyFee.phoneBrand.NotNull.message}")
    private String phoneBrand;      //手机品牌

    @NotBlank(message = "{policyFee.phoneModel.NotBlank.message}")
    private String phoneModel;          //手机型号

    @NotNull(message = "{policyFee.policyFee.NotNull.message}")
    private BigDecimal policyFee;       //保单费用

    @NotNull(message = "{policyFee.sort.NotNull.message}")
    private Integer sort;               //排序

    @NotNull(message = "{policyFee.status.NotNull.message}")
    private EnableStatus status;        //状态

    @NotNull(message = "{policyFee.policyMoney.NotNull.message}")
    private BigDecimal policyMoney;     //保单金额

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

    public BigDecimal getPolicyFee() {
        return policyFee;
    }

    public void setPolicyFee(BigDecimal policyFee) {
        this.policyFee = policyFee;
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

    public BigDecimal getPolicyMoney() {
        return policyMoney;
    }

    public void setPolicyMoney(BigDecimal policyMoney) {
        this.policyMoney = policyMoney;
    }
}
