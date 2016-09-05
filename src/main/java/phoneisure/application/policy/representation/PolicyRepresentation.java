package phoneisure.application.policy.representation;

import phoneisure.application.merchant.representation.MerchantRepresentation;
import phoneisure.core.enums.PolicyStatus;
import phoneisure.domain.model.idtype.IdType;
import phoneisure.domain.model.picture.Picture;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by LvDi on 2016/4/22.
 */
public class PolicyRepresentation {

    private String id;
    private Integer version;
    private Date createDate;

    private String policyNo;                       //保单号
    private MerchantRepresentation merchant;                     //代理商户
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


    public MerchantRepresentation getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantRepresentation merchant) {
        this.merchant = merchant;
    }

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

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
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

    public BigDecimal getPolicyMoney() {
        return policyMoney;
    }

    public void setPolicyMoney(BigDecimal policyMoney) {
        this.policyMoney = policyMoney;
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

    public List<Picture> getInsuredBeginPicture() {
        return insuredBeginPicture;
    }

    public void setInsuredBeginPicture(List<Picture> insuredBeginPicture) {
        this.insuredBeginPicture = insuredBeginPicture;
    }

    public List<Picture> getInsuredAfterPicture() {
        return insuredAfterPicture;
    }

    public void setInsuredAfterPicture(List<Picture> insuredAfterPicture) {
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

    public Date getInsuredBeginDate() {
        return insuredBeginDate;
    }

    public void setInsuredBeginDate(Date insuredBeginDate) {
        this.insuredBeginDate = insuredBeginDate;
    }

    public Date getInsuredEndDate() {
        return insuredEndDate;
    }

    public void setInsuredEndDate(Date insuredEndDate) {
        this.insuredEndDate = insuredEndDate;
    }

    public PolicyStatus getPolicyStatus() {
        return policyStatus;
    }

    public void setPolicyStatus(PolicyStatus policyStatus) {
        this.policyStatus = policyStatus;
    }

    public String getIMEI() {
        return IMEI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }
}
