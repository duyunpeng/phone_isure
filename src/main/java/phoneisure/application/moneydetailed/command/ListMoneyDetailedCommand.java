package phoneisure.application.moneydetailed.command;

import phoneisure.core.commons.BasicPaginationCommand;
import phoneisure.core.enums.FlowType;


/**
 * Created by dyp on 16-4-22.
 */
public class ListMoneyDetailedCommand extends BasicPaginationCommand{
    private String merchant;      //商户
    private String createDate;    //数据创建时间

    private String startDealTime;
    private String endDealTime;

    private String agent;

    private FlowType flowType;

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

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public FlowType getFlowType() {
        return flowType;
    }

    public void setFlowType(FlowType flowType) {
        this.flowType = flowType;
    }
}