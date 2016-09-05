package phoneisure.core.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YJH on 2016/4/15.
 */
public enum ApiReturnCode {

    // 通用错误码
    ERROR_UNKNOWN("未知错误", 0, Boolean.FALSE),
    NO_FOUND("数据不存在", 1, Boolean.FALSE),
    SUCCESS("处理成功", 2, Boolean.FALSE),
    FAILURE("处理失败", 3, Boolean.FALSE),

    //api 错误码
    AUTHENTICATION_FAILURE("鉴权失败", 1000, Boolean.FALSE),
    ILLEGAL_ARGUMENT("不合法参数", 1000, Boolean.FALSE),

    ERROR_ACCOUNT_EXIST("用户名已存在", 1100, Boolean.FALSE),
    ERROR_DATA_NOT_FOUND("相关数据没有找到", 1101, Boolean.FALSE),
    ERROR_AGENT_EXIST("此区域以存在代理", 1102, Boolean.FALSE),


    ERROR_PASSWORD_NOT_EQ("旧密码不相等", 1200, Boolean.FALSE),
    ERROR_MONEY_NOT_ENOUGH("余额不足", 12001, Boolean.FALSE),
    ERROR_AGENT_NO_FOUND("代理不存在", 12002, Boolean.FALSE),
    ERROR_POLICY_DATE_REPEAT_SUBMIT("数据重复提交", 12003, Boolean.FALSE),
    ERROR_POLICY_IMEI_Exist("IMEI号已存在", 12004, Boolean.FALSE),
    ERROR_POLICY_TIME_OUT_Exist("保单操作过时", 12005, Boolean.FALSE);

    ApiReturnCode(String name, int value, Boolean onlyQuery) {
        this.name = name;
        this.value = value;
        this.onlyQuery = onlyQuery;
    }

    private static Map<Integer, ApiReturnCode> CACHE = new HashMap<Integer, ApiReturnCode>(ApiReturnCode.values().length * 2);

    static {
        for (ApiReturnCode apiReturnCode : ApiReturnCode.values()) {
            CACHE.put(apiReturnCode.getValue(), apiReturnCode);
        }
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

    public static ApiReturnCode valueOf(int value) {
        return CACHE.get(value);
    }
}
