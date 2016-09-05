package phoneisure.core.enums;

/**
 * Created by YJH on 2016/4/22.
 */
public enum PolicyStatus {

    ALL("全部", 0, Boolean.TRUE),
    NORMAL("正常", 1, Boolean.FALSE),
    BACK("退单中", 2, Boolean.FALSE),
    NEED_CLAIM("需要理赔", 3, Boolean.FALSE),
    SUCCESS_CLAIM("理赔完成", 4, Boolean.FALSE),
    SUCCESS_BACK("已退单", 5, Boolean.FALSE),;

    private PolicyStatus(String name, int value, Boolean onlyQuery) {
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
