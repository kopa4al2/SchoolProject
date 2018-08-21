package justme.projectAwesome.controllers;

import org.springframework.web.servlet.ModelAndView;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

public abstract class BaseController {

    protected ModelAndView view(String view) {
        return new ModelAndView(view);
    }

     protected  ModelAndView view(String view, String name, Object value) {
        return new ModelAndView(view, name, value);
     }

    protected ModelAndView redirect(String redirectValue) {

        return new ModelAndView("redirect:" + redirectValue);
    }

    protected String getLoggedInUsername() {
        return getContext().getAuthentication().getName();
    }
}
