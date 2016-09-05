package phoneisure.core.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 * Created by YJH on 2016/4/22.
 */
public class ApiRequest extends BasicApi {

    private Object object;  //数据

    @Override
    public NameValuePair toNameValuePair() {
        String jsonString = null;
        try {
            jsonString = JSON.toJSONString(this);
        } catch (JSONException e) {
            log.error("转换json异常.", e);
        }
        return new BasicNameValuePair(ApiConstant.API_REQUEST_PARAMETER_NAME, jsonString);
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
