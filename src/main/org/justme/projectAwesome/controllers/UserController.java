package justme.projectAwesome.controllers;

import jdk.jshell.spi.ExecutionControl;
import justme.projectAwesome.models.binding.UserRegisterBindingModel;
import justme.projectAwesome.services.interfaces.UserService;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView loginPage(@RequestParam(required = false) String error,
                                  ModelAndView modelAndView) {
        if(error != null) {
            //TODO: Error handling
            throw new NotYetImplementedException(error);
        }
        modelAndView.setViewName("login-page");
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView register(@RequestParam(required = false) String error,
                                 @ModelAttribute UserRegisterBindingModel bindingModel,
                                 ModelAndView modelAndView) {
        if(error != null) {
            throw new NotYetImplementedException(error);
        }
        modelAndView.setViewName("redirect:/login");

        if(bindingModel.getPassword().equals(
                bindingModel.getConfirmPassword())) {
            this.userService.createUser(bindingModel);
        }
        else{
            //TODO: passwords dont match
        }
        return modelAndView;
    }

    @GetMapping("/users/userid={userId}")
    public ModelAndView getSingleUser(ModelAndView modelAndView,
                                      @PathVariable String userId) {
        modelAndView.setViewName("single-user-page");
        try {
            modelAndView.addObject("user", this.userService.findById(userId).get());
        } catch (NoSuchElementException nse) {
//            TODO: User not found error
        }
        return modelAndView;
    }
}
