package phoneisure.core.enums;

/**
 * Created by YJH on 2016/5/5.
 */
public enum RechargeStatus {

    ALL("全部", 0, Boolean.TRUE),
    WAIT("待支付", 1, Boolean.FALSE),
    SUCCESS("成功", 2, Boolean.FALSE),
    FAIL("失败", 3, Boolean.FALSE);

    private RechargeStatus(String name, int value, Boolean onlyQuery) {
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
