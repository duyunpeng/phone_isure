package phoneisure.application.policyfee.representation;

import phoneisure.application.phonebrand.representation.PhoneBrandRepresentation;
import phoneisure.core.enums.EnableStatus;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by LvDi on 2016/4/23.
 */
public class PolicyFeeRepresentation {


    private String id;
    private Integer version;
    private Date createDate;

    private PhoneBrandRepresentation phoneBrand;      //手机品牌
    private String phoneModel;          //手机型号
    private BigDecimal policyFee;       //保单费用
    private Integer sort;               //排序
    private EnableStatus status;        //状态
    private BigDecimal policyMoney;     //保单金额

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public PhoneBrandRepresentation getPhoneBrand() {
        return phoneBrand;
    }

    public void setPhoneBrand(PhoneBrandRepresentation phoneBrand) {
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
