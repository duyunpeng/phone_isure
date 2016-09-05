package phoneisure.core.enums;

/**
 * Created by pengyi on 2016/3/30.
 */
public enum PayType {

    All("全部", 0, Boolean.TRUE),
    OFFLINE("线下支付", 1, Boolean.FALSE),
    BALANCE("余额支付", 2, Boolean.FALSE),
    WECHAT("微信支付", 3, Boolean.FALSE),
    ALIPAY("支付宝支付", 3, Boolean.FALSE);

    private PayType(String name, int value, Boolean onlyQuery) {
        this.name = name;
        this.value = value;
        this.onlyQuery = onlyQuery;
    }

    private String name;

    private int value;

    private Boolean onlyQuery;                  // 仅用于页面查询和业务逻辑无关

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

    public Boolean isOnlyQuery() {
        return onlyQuery;
    }
}
