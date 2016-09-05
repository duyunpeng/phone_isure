package phoneisure.core.exception;

/**
 * Created by YJH on 2016/4/26.
 */
public class PasswordNotEquals extends RuntimeException {

    public PasswordNotEquals() {
    }

    public PasswordNotEquals(String message) {
        super(message);
    }

    public PasswordNotEquals(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordNotEquals(Throwable cause) {
        super(cause);
    }
}
