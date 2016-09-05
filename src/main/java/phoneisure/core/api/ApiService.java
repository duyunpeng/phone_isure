package phoneisure.core.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import phoneisure.core.commons.CharsetConstant;
import phoneisure.core.exception.ApiRemoteCallFailedException;
import phoneisure.core.util.CoreHttpUtils;
import phoneisure.core.util.CoreStringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by YJH on 2016/4/22.
 */
public class ApiService implements IApiService {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    protected ApiConfig apiConfig;

    public void setApiConfig(ApiConfig apiConfig) {
        this.apiConfig = apiConfig;
    }

    /**
     *
     * @param apiRequest
     * @param timeout       毫秒
     * @return
     * @throws ApiRemoteCallFailedException
     */
    @Override
    public ApiResponse request(BasicApi apiRequest, int timeout) throws ApiRemoteCallFailedException {
        return this.request(apiConfig, apiRequest, timeout);
    }
    /**
     * 请求默认超时时间10s
     * @param apiRequest
     * @return
     * @throws ApiRemoteCallFailedException
     */
    @Override
    public ApiResponse request(BasicApi apiRequest) throws ApiRemoteCallFailedException {
        int timeout = ApiConstant.API_REQUEST_TIME_OUT_DEFAULT;
        return request(apiRequest, timeout);
    }

    /**
     *
     * @param apiConfig
     * @param apiRequest
     * @param timeout       毫秒
     * @return
     * @throws ApiRemoteCallFailedException
     */
    public ApiResponse request(ApiConfig apiConfig, BasicApi apiRequest, int timeout)
            throws ApiRemoteCallFailedException {
        return requestInternal(apiConfig, apiRequest, timeout);
    }

    protected ApiResponse requestInternal(ApiConfig apiConfig, BasicApi apiRequest, int timeout)
            throws ApiRemoteCallFailedException {

        if (null == apiRequest || null == apiConfig) {
            log.error("Api调用失败, apiRequest[{}] apiConfig[{}]", apiRequest, apiConfig);
            throw new ApiRemoteCallFailedException("API响应结果为空", ApiResponse.DEFAULT_FAILED);
        }

        String baseUrl = apiConfig.getBasicUrl();
        String url = apiRequest.getUrl();
        log.info("Api调用地址: [{}{}]", baseUrl, url);

        List<NameValuePair> parameterList = new ArrayList<NameValuePair>();
        // 组装默认参数
        Map<String, String> parameterMap = apiConfig.getParameterMap();
        if (parameterMap != null) {
            for (Map.Entry<String, String> entry : parameterMap.entrySet()) {
                String key = CoreStringUtils.urlEncode(entry.getKey(), CharsetConstant.DEFAULT_STRING);
                String value = CoreStringUtils.urlEncode(entry.getValue(), CharsetConstant.DEFAULT_STRING);
                parameterList.add(new BasicNameValuePair(key, value));
                log.info("添加默认请求参数: key[{}], value[{}]", entry.getKey(), entry.getValue());
            }
        }

        // 组装请求参数
        NameValuePair json = apiRequest.toNameValuePair();
        parameterList.add(json);
        log.info("添加请求参数: key[{}], value[{}]", json.getName(), CoreStringUtils.urlDecode(json.getValue(), CharsetConstant.UTF8_STRING));


        RequestConfig requestConfig = RequestConfig.copy(CoreHttpUtils.DEFAULT_REQUEST_CONFIG)
                .setConnectionRequestTimeout(timeout)
                .setConnectTimeout(timeout)
                .build();

        String result = null;
        try {
            result = CoreHttpUtils.post(baseUrl + url,
                    CharsetConstant.UTF8_CHARSET,
                    null,
                    requestConfig,
                    null,
                    null,
                    parameterList.toArray(new NameValuePair[parameterList.size()]));
        } catch (Exception e) {
            log.warn("AP请求失败, Api调用地址: [{}{}], 参数: [{}].", baseUrl, url, getRequestParameter(parameterList), e);
            log.info("响应结果: [{}]", CoreStringUtils.urlDecode(result, CharsetConstant.UTF8_STRING));
            throw new ApiRemoteCallFailedException("API请求失败", ApiResponse.DEFAULT_FAILED);
        }

        if (CoreStringUtils.isEmpty(result)) {
            log.error("响应结果为空, 非法");
            throw new ApiRemoteCallFailedException("API响应结果为空", ApiResponse.DEFAULT_FAILED);
        }

        // 转换unicode到可识别的中文 php可能输出json时有BOM
        if (result.length() > 0 && result.charAt(0) == '\ufeff') {
            result = result.substring(1);
        }

        result = CoreStringUtils.unicodeToString(result);

        ApiResponse response;
        try {
            response = JSON.parseObject(result, ApiResponse.class);
            log.info("Api请求成功, code: [{}]", response.getCode());
            log.info("响应结果: [{}]", CoreStringUtils.urlDecode(result, CharsetConstant.UTF8_STRING));
        } catch (JSONException e) {
            log.warn("API调用返回结果格式不正确", e);
            log.info("响应结果: [{}]", CoreStringUtils.urlDecode(result, CharsetConstant.UTF8_STRING));
            throw new ApiRemoteCallFailedException("API响应结果Json转换出错", ApiResponse.DEFAULT_FAILED);
        }

        log.info("返回消息，message: [{}]", response.getMessage());

        if (ApiReturnCode.SUCCESS == response.getCode()) {
        } else {
            log.warn("AP请求失败, Api调用地址: [{}{}], 参数: [{}]", baseUrl, url, getRequestParameter(parameterList));
            log.info("响应结果: [{}]", CoreStringUtils.urlDecode(result, CharsetConstant.UTF8_STRING));
            throw new ApiRemoteCallFailedException("API请求失败,返回内容: code[" +
                    response.getCode() + "] message[" + response.getMessage() + "]", response);
        }

        return response;
    }

    /**
     * 获取请求参数字符串
     * @param list
     * @return
     */
    protected String getRequestParameter(List<NameValuePair> list) {
        StringBuilder stringBuilder = new StringBuilder();

        for (Iterator<NameValuePair> iterator = list.iterator(); iterator.hasNext();) {
            NameValuePair nameValuePair = iterator.next();
            stringBuilder.append(nameValuePair.toString());
            if (iterator.hasNext()) {
                stringBuilder.append("&");
            }
        }

        return stringBuilder.toString();
    }
}
