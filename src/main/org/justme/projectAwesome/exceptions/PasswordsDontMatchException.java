package justme.projectAwesome.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PasswordsDontMatchException extends RuntimeException {
    private static final String DEFAULT_ERROR_MESSAGE = "Passwords dont match";

    private String message = DEFAULT_ERROR_MESSAGE;

    public PasswordsDontMatchException(String message) {
        super(message);
        this.message = message;
    }

    public PasswordsDontMatchException() {
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
