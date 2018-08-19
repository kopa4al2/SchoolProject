package justme.projectAwesome.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private static final String DEFAULT_ERROR_MESSAGE = "We couldn't find what you're looking for";

    private String message = DEFAULT_ERROR_MESSAGE;

    public NotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public NotFoundException() {
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
