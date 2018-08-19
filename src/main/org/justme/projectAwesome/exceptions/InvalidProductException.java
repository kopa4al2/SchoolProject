package justme.projectAwesome.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE)
public class InvalidProductException extends RuntimeException {

    private static final String DEFAULT_ERROR_MESSAGE = "You cant upload this product";

    private String message = DEFAULT_ERROR_MESSAGE;

    public InvalidProductException() {
    }

    public InvalidProductException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
