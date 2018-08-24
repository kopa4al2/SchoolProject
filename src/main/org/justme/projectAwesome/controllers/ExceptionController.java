package justme.projectAwesome.controllers;

import justme.projectAwesome.annotations.ExceptionView;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@ControllerAdvice
public class ExceptionController extends BaseController {

    private static final String DEFAULT_ERROR_VIEW = "errors/error";

    @ExceptionHandler(Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e){
        StackTraceElement stackTraceElement = e.getStackTrace()[0];
        Method handlerMethod = null;
        try {
            Class<?> clazz = Class.forName(stackTraceElement.getClassName());
            for (Method method : clazz.getDeclaredMethods()) {
                if(method.getName().equals(stackTraceElement.getMethodName()))
                    handlerMethod = method;
            }
        } catch (Exception exc) {

        }
        String targetView;
        if (handlerMethod != null && handlerMethod.isAnnotationPresent(ExceptionView.class)) {
            String value = AnnotationUtils.getAnnotation(handlerMethod, ExceptionView.class).value();
            targetView = value;
        } else {
            targetView = DEFAULT_ERROR_VIEW; // kind of a fallback
            return super.view(targetView, "error", e.getMessage());
        }

        return super.redirect(targetView, "error", e.getMessage());
    }

}
