package phoneisure.core.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import phoneisure.core.commons.CharsetConstant;
import phoneisure.core.util.CoreStringUtils;

/**
 * Created by YJH on 2016/4/15.
 */
public class ApiResponse {

    protected static Logger logger = LoggerFactory.getLogger(ApiResponse.class.getName());

    public static ApiResponse DEFAULT_FAILED = new ApiResponse(ApiReturnCode.FAILURE);

    private ApiReturnCode code;     // API执行结果

    private String message;         // 执行结果信息

    private long debugTime;       // 远程调用执行时间，仅在调试模式时有此返回值，单位：s

    private String data;           //返回数据

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        ApiResponse.logger = logger;
    }

    public ApiReturnCode getCode() {
        return code;
    }

    public void setCode(ApiReturnCode code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getDebugTime() {
        return debugTime;
    }

    public void setDebugTime(long debugTime) {
        this.debugTime = debugTime;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ApiResponse() {
    }

    public ApiResponse(ApiReturnCode code) {
        this(code, code.getName(), null);
    }

    public ApiResponse(ApiReturnCode code, String message, Object data) {
        this(code, message, 0, internalToJsonString(data));
    }

    public ApiResponse(ApiReturnCode code, String message, long debugTime, String data) {
        this.code = code;
        this.message = message;
        this.debugTime = debugTime;
        this.data = data;
    }

    public <T> T convertJsonTo(TypeReference<T> clazz) {
        T t = null;
        try {
            t = JSON.parseObject(CoreStringUtils.urlDecode(this.getData(), CharsetConstant.DEFAULT_STRING),
                    clazz);
        } catch (JSONException e) {
            logger.error("转换json异常.", e);
        }
        return t;
    }


    private static final String internalToJsonString(Object object) {
        String jsonString = null;
        try {
            jsonString = JSON.toJSONString(object);
        } catch (JSONException e) {
            logger.error("转换json异常.", e);
        }
        return null == jsonString ? jsonString : jsonString;
    }

}
