package phoneisure.application.policy.command;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by YJH on 2016/4/25.
 */
public class CreatePolicyCommand {

    private String merchant;    //代理商户
    private String phoneModel;  //手机型号
    private BigDecimal policyFee;   //保费
    private String insuredName;                     //参保人姓名
    private String insuredPhone;                    //参保人手机号码
    private List<String> insuredBeginPicture;      //参保手机新机图片
    private String idType;      //证件类型
    private String idNumber;    //证件号码
    private String IMEI;        //手机串号

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

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }
}
