package justme.projectAwesome.controllers;

import justme.projectAwesome.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController extends BaseController {

    private static final String DEFAULT_ERROR_VIEW = "errors/error";
    private static final String NOT_FOUND_ERROR_VIEW = "/errors/not-found";

    @ExceptionHandler(Exception.class)
    public ModelAndView handleErrorDefault(Exception e) {

        return this.view(DEFAULT_ERROR_VIEW, "error", e.getMessage());
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ModelAndView renderError(NotFoundException e) {
        return this.view(NOT_FOUND_ERROR_VIEW, "error", e.getMessage());
    }
}
