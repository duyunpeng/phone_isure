package phoneisure.core.enums;

/**
 * Created by YJH on 2016/4/15.
 */
public enum AuthStatus {

    ALL("全部", 0, Boolean.TRUE),
    NOT("未审核", 1, Boolean.FALSE),
    SUCCESS("审核通过", 2, Boolean.FALSE),
    FAILURE("审核驳回", 3, Boolean.FALSE);

    private AuthStatus(String name, int value, Boolean onlyQuery) {
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
