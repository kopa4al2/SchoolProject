package justme.projectAwesome.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CategoryAlreadyExistsException extends RuntimeException {

    private static final String DEFAULT_ERROR_MESSAGE = "There is already such category";

    private String message = DEFAULT_ERROR_MESSAGE;

    public CategoryAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }

    public CategoryAlreadyExistsException() {
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
