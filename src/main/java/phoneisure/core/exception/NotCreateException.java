package phoneisure.core.exception;

/**
 * Created by YJH
 * Date : 16-7-20.
 */
public class NotCreateException extends RuntimeException {

    public NotCreateException() {
    }

    public NotCreateException(String message) {
        super(message);
    }

    public NotCreateException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotCreateException(Throwable cause) {
        super(cause);
    }

}
