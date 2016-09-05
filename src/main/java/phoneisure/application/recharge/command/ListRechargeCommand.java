package phoneisure.application.recharge.command;

import phoneisure.core.commons.BasicPaginationCommand;
import phoneisure.core.enums.YesOrNoStatus;

/**
 * Created by dyp on 2016/4/22.
 */
public class ListRechargeCommand extends BasicPaginationCommand{

    private String startDealTime;
    private String endDealTime;

    private String merchant;          //充值商户
    private String createDate;    //数据创建时间
    private YesOrNoStatus isPay;        //是否支付


    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getEndDealTime() {
        return endDealTime;
    }

    public void setEndDealTime(String endDealTime) {
        this.endDealTime = endDealTime;
    }

    public String getStartDealTime() {
        return startDealTime;
    }

    public void setStartDealTime(String startDealTime) {
        this.startDealTime = startDealTime;
    }

    public YesOrNoStatus getIsPay() {
        return isPay;
    }

    public void setIsPay(YesOrNoStatus isPay) {
        this.isPay = isPay;
    }
}
