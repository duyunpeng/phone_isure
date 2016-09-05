package phoneisure.domain.model.policy;

import phoneisure.core.enums.PolicyStatus;
import phoneisure.core.id.ConcurrencySafeEntity;
import phoneisure.domain.model.idtype.IdType;
import phoneisure.domain.model.merchant.Merchant;
import phoneisure.domain.model.picture.Picture;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by YJH on 2016/4/22.
 */
public class Policy extends ConcurrencySafeEntity {

    private String policyNo;                       //保单号
    private Merchant merchant;                     //代理商户
    private String phoneModel;                      //手机型号
    private BigDecimal policyFee;                   //保单费用
    private BigDecimal policyMoney;                 //保单金额
    private String insuredName;                     //参保人姓名
    private String insuredPhone;                    //参保人手机号码
    private List<Picture> insuredBeginPicture;      //参保手机新机图片
    private List<Picture> insuredAfterPicture;      //参保手机理赔之后照片
    private IdType idType;                          //证件类型
    private String idNumber;                        //证件号码
    private Date insuredBeginDate;                  //参保时间开始
    private Date insuredEndDate;                    //参保时间结束
    private PolicyStatus policyStatus;              //状态
    private String IMEI;                            //手机串号

    public void changeDate(Date date) {
        this.setCreateDate(date);
    }

    public void changePolicyStatus(PolicyStatus policyStatus) {
        this.policyStatus = policyStatus;
    }

    public void changeInsuredAfterPicture(List<Picture> insuredAfterPicture) {
        this.insuredAfterPicture = insuredAfterPicture;
    }

    public void changeInsuredEndDate(Date insuredEndDate) {
        this.insuredEndDate = insuredEndDate;
    }

    private void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    private void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    private void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

    private void setPolicyFee(BigDecimal policyFee) {
        this.policyFee = policyFee;
    }

    private void setPolicyMoney(BigDecimal policyMoney) {
        this.policyMoney = policyMoney;
    }

    private void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    private void setInsuredPhone(String insuredPhone) {
        this.insuredPhone = insuredPhone;
    }

    private void setInsuredBeginPicture(List<Picture> insuredBeginPicture) {
        this.insuredBeginPicture = insuredBeginPicture;
    }

    private void setInsuredAfterPicture(List<Picture> insuredAfterPicture) {
        this.insuredAfterPicture = insuredAfterPicture;
    }

    private void setIdType(IdType idType) {
        this.idType = idType;
    }

    private void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    private void setInsuredBeginDate(Date insuredBeginDate) {
        this.insuredBeginDate = insuredBeginDate;
    }

    private void setInsuredEndDate(Date insuredEndDate) {
        this.insuredEndDate = insuredEndDate;
    }

    private void setPolicyStatus(PolicyStatus policyStatus) {
        this.policyStatus = policyStatus;
    }

    private void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public BigDecimal getPolicyFee() {
        return policyFee;
    }

    public BigDecimal getPolicyMoney() {
        return policyMoney;
    }

    public String getInsuredName() {
        return insuredName;
    }

    public String getInsuredPhone() {
        return insuredPhone;
    }

    public List<Picture> getInsuredBeginPicture() {
        return insuredBeginPicture;
    }

    public List<Picture> getInsuredAfterPicture() {
        return insuredAfterPicture;
    }

    public IdType getIdType() {
        return idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public Date getInsuredBeginDate() {
        return insuredBeginDate;
    }

    public Date getInsuredEndDate() {
        return insuredEndDate;
    }

    public PolicyStatus getPolicyStatus() {
        return policyStatus;
    }

    public String getIMEI() {
        return IMEI;
    }

    public Policy() {
        super();
    }

    public Policy(String policyNo, Merchant merchant, String phoneModel, BigDecimal policyFee, BigDecimal policyMoney, String insuredName, String insuredPhone, List<Picture> insuredBeginPicture, List<Picture> insuredAfterPicture, IdType idType, String idNumber, Date insuredBeginDate, Date insuredEndDate, PolicyStatus policyStatus, String IMEI) {
        this.policyNo = policyNo;
        this.merchant = merchant;
        this.phoneModel = phoneModel;
        this.policyFee = policyFee;
        this.policyMoney = policyMoney;
        this.insuredName = insuredName;
        this.insuredPhone = insuredPhone;
        this.insuredBeginPicture = insuredBeginPicture;
        this.insuredAfterPicture = insuredAfterPicture;
        this.idType = idType;
        this.idNumber = idNumber;
        this.insuredBeginDate = insuredBeginDate;
        this.insuredEndDate = insuredEndDate;
        this.policyStatus = policyStatus;
        this.IMEI = IMEI;
        this.setCreateDate(new Date());
    }
}
