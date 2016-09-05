package phoneisure.core.exception;

/**
 * Created by YJH
 * Date : 16-7-25.
 */
public class TimeOutException extends RuntimeException {

    public TimeOutException() {
    }

    public TimeOutException(String message) {
        super(message);
    }

    public TimeOutException(String message, Throwable cause) {
        super(message, cause);
    }

    public TimeOutException(Throwable cause) {
        super(cause);
    }

}
