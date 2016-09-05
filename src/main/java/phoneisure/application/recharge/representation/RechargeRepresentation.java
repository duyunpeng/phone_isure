package phoneisure.application.recharge.representation;

import phoneisure.application.merchant.representation.MerchantRepresentation;
import phoneisure.core.enums.PayType;
import phoneisure.core.enums.YesOrNoStatus;
import phoneisure.domain.model.merchant.Merchant;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by dyp on 2016/4/22.
 */
public class RechargeRepresentation {

    private String id;
    private Integer version;
    private Date createDate;

    private String rechargeNo;            //充值流水号
    private MerchantRepresentation merchant;          //充值商户
    private BigDecimal rechargeMoney;    //充值金额
    private Date payDate;               //支付时间
    private PayType payType;            //支付方式
    private String payNo;               //支付号
    private YesOrNoStatus isPay;        //是否支付
    private String ipAddress;           //ip

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public YesOrNoStatus getIsPay() {
        return isPay;
    }

    public void setIsPay(YesOrNoStatus isPay) {
        this.isPay = isPay;
    }

    public MerchantRepresentation getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantRepresentation merchant) {
        this.merchant = merchant;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public BigDecimal getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(BigDecimal rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getRechargeNo() {
        return rechargeNo;
    }

    public void setRechargeNo(String rechargeNo) {
        this.rechargeNo = rechargeNo;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
