package phoneisure.domain.model.recharge;

import phoneisure.core.enums.PayType;
import phoneisure.core.enums.RechargeStatus;
import phoneisure.core.enums.YesOrNoStatus;
import phoneisure.core.id.ConcurrencySafeEntity;
import phoneisure.domain.model.merchant.Merchant;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by YJH on 2016/4/21.
 */
public class Recharge extends ConcurrencySafeEntity {

    private String rechargeNo;            //充值流水号
    private Merchant merchant;          //充值商户
    private BigDecimal rechargeMoney;    //充值金额
    private Date payDate;               //支付时间
    private PayType payType;            //支付方式
    private String payNo;               //支付号
    private YesOrNoStatus isPay;        //是否支付
    private RechargeStatus status;      //充值状态
    private String ipAddress;           //操作ip

    public void changePayDate(Date payDate) {
        this.payDate = payDate;
    }

    public void changePayType(PayType payType) {
        this.payType = payType;
    }

    public void changePayNo(String payNo) {
        this.payNo = payNo;
    }

    public void changeIsPay(YesOrNoStatus isPay) {
        this.isPay = isPay;
    }

    public void changeStatus(RechargeStatus status) {
        this.status = status;
    }

    public void changeIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    private void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    private void setRechargeMoney(BigDecimal rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    private void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    private void setPayType(PayType payType) {
        this.payType = payType;
    }

    private void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    private void setIsPay(YesOrNoStatus isPay) {
        this.isPay = isPay;
    }

    private void setRechargeNo(String rechargeNo) {
        this.rechargeNo = rechargeNo;
    }

    private void setStatus(RechargeStatus status) {
        this.status = status;
    }

    private void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public BigDecimal getRechargeMoney() {
        return rechargeMoney;
    }

    public Date getPayDate() {
        return payDate;
    }

    public PayType getPayType() {
        return payType;
    }

    public String getPayNo() {
        return payNo;
    }

    public YesOrNoStatus getIsPay() {
        return isPay;
    }

    public RechargeStatus getStatus() {
        return status;
    }

    public String getRechargeNo() {
        return rechargeNo;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public Recharge() {
        super();
    }

    public Recharge(String rechargeNo, Merchant merchant, BigDecimal rechargeMoney, Date payDate, PayType payType, String payNo, YesOrNoStatus isPay, RechargeStatus status, String ipAddress) {
        this.rechargeNo = rechargeNo;
        this.merchant = merchant;
        this.rechargeMoney = rechargeMoney;
        this.payDate = payDate;
        this.payType = payType;
        this.payNo = payNo;
        this.isPay = isPay;
        this.status = status;
        this.ipAddress = ipAddress;
        this.setCreateDate(new Date());
    }
}
