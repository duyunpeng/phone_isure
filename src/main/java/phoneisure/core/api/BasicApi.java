package phoneisure.core.api;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.http.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * Created by YJH on 2016/4/22.
 */
public abstract class BasicApi implements Serializable {

    protected Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @JSONField(serialize = false)
    private String url;

    public abstract NameValuePair toNameValuePair();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
