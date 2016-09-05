package phoneisure.domain.model.policyfee;

import phoneisure.core.enums.EnableStatus;
import phoneisure.core.id.ConcurrencySafeEntity;
import phoneisure.domain.model.phonebrand.PhoneBrand;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by YJH on 2016/4/22.
 */
public class PolicyFee extends ConcurrencySafeEntity {

    private PhoneBrand phoneBrand;      //手机品牌
    private String phoneModel;          //手机型号
    private BigDecimal policyFee;       //保单费用
    private BigDecimal policyMoney;     //保单金额
    private Integer sort;               //排序
    private EnableStatus status;        //状态

    public void changePhoneBrand(PhoneBrand phoneBrand) {
        this.phoneBrand = phoneBrand;
    }

    public void changePhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

    public void changePolicyFee(BigDecimal policyFee) {
        this.policyFee = policyFee;
    }

    public void changePolicyMoney(BigDecimal policyMoney) {
        this.policyMoney = policyMoney;
    }

    public void changeSort(Integer sort) {
        this.sort = sort;
    }

    public void changeStatus(EnableStatus status) {
        this.status = status;
    }

    private void setPhoneBrand(PhoneBrand phoneBrand) {
        this.phoneBrand = phoneBrand;
    }

    private void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

    private void setPolicyFee(BigDecimal policyFee) {
        this.policyFee = policyFee;
    }

    private void setSort(Integer sort) {
        this.sort = sort;
    }

    private void setStatus(EnableStatus status) {
        this.status = status;
    }

    private void setPolicyMoney(BigDecimal policyMoney) {
        this.policyMoney = policyMoney;
    }

    public PhoneBrand getPhoneBrand() {
        return phoneBrand;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public BigDecimal getPolicyFee() {
        return policyFee;
    }

    public Integer getSort() {
        return sort;
    }

    public EnableStatus getStatus() {
        return status;
    }

    public BigDecimal getPolicyMoney() {
        return policyMoney;
    }

    public PolicyFee() {
        super();
    }

    public PolicyFee(PhoneBrand phoneBrand, String phoneModel, BigDecimal policyFee, Integer sort, EnableStatus status, BigDecimal policyMoney) {
        this.phoneBrand = phoneBrand;
        this.phoneModel = phoneModel;
        this.policyFee = policyFee;
        this.sort = sort;
        this.status = status;
        this.policyMoney = policyMoney;
        this.setCreateDate(new Date());
    }
}
