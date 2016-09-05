package phoneisure.application.policy.command;

import phoneisure.application.merchant.representation.MerchantRepresentation;
import phoneisure.core.enums.PolicyStatus;
import phoneisure.domain.model.idtype.IdType;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by YJH on 2016/5/7.
 */
public class HandlePolicyCommand {

    private String id;
    private Integer version;
    private String createDate;

    private String policyNo;                       //保单号
    private String merchant;                     //代理商户
    private String phoneModel;                      //手机型号
    private BigDecimal policyFee;                   //保单费用
    private String insuredName;                     //参保人姓名
    private String insuredPhone;                    //参保人手机号码
    private List<String> insuredBeginPicture;      //参保手机新机图片
    @NotNull(message = "{policy.insuredAfterPicture.NotNull.message}")
    private List<String> insuredAfterPicture;      //参保手机理赔之后照片
    private IdType idType;                          //证件类型
    private String idNumber;                        //证件号码
    private String insuredBeginDate;                  //参保时间开始
    private String insuredEndDate;                    //参保时间结束
    private PolicyStatus policyStatus;              //状态

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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
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

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    public String getInsuredPhone() {
        return insuredPhone;
    }

    public void setInsuredPhone(String insuredPhone) {
        this.insuredPhone = insuredPhone;
    }

    public List<String> getInsuredBeginPicture() {
        return insuredBeginPicture;
    }

    public void setInsuredBeginPicture(List<String> insuredBeginPicture) {
        this.insuredBeginPicture = insuredBeginPicture;
    }

    public List<String> getInsuredAfterPicture() {
        return insuredAfterPicture;
    }

    public void setInsuredAfterPicture(List<String> insuredAfterPicture) {
        this.insuredAfterPicture = insuredAfterPicture;
    }

    public IdType getIdType() {
        return idType;
    }

    public void setIdType(IdType idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getInsuredBeginDate() {
        return insuredBeginDate;
    }

    public void setInsuredBeginDate(String insuredBeginDate) {
        this.insuredBeginDate = insuredBeginDate;
    }

    public String getInsuredEndDate() {
        return insuredEndDate;
    }

    public void setInsuredEndDate(String insuredEndDate) {
        this.insuredEndDate = insuredEndDate;
    }

    public PolicyStatus getPolicyStatus() {
        return policyStatus;
    }

    public void setPolicyStatus(PolicyStatus policyStatus) {
        this.policyStatus = policyStatus;
    }
}
