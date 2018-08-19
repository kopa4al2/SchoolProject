package justme.projectAwesome.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ExceptionController extends BaseController{

    private static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView renderError(HttpServletResponse response, Exception e) throws Exception {
        int code = response.getStatus();
        return this.view(DEFAULT_ERROR_VIEW, "error", e.getMessage());
    }
}
