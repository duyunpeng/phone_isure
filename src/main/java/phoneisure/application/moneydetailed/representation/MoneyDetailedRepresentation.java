package phoneisure.application.moneydetailed.representation;

import phoneisure.application.merchant.representation.MerchantRepresentation;
import phoneisure.core.enums.FlowType;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by dyp on 16-4-22.
 */
public class MoneyDetailedRepresentation {

    private String id;
    private Integer version;
    private Date createDate;


    private MerchantRepresentation merchant;      //商户
    private FlowType flowType;      //流向类型
    private BigDecimal money;       //金额
    private String explain;         //说明
    private String insuredName;     //参保人姓名

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

    public MerchantRepresentation getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantRepresentation merchant) {
        this.merchant = merchant;
    }

    public FlowType getFlowType() {
        return flowType;
    }

    public void setFlowType(FlowType flowType) {
        this.flowType = flowType;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }
}
