package phoneisure.core.exception;

/**
 * 并发异常
 *
 * Created by YJH on 2016/3/7.
 */
public class WechatSignException extends RuntimeException {

    public WechatSignException() {
    }

    public WechatSignException(String message) {
        super(message);
    }

    public WechatSignException(String message, Throwable cause) {
        super(message, cause);
    }

    public WechatSignException(Throwable cause) {
        super(cause);
    }

}
