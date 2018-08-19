package justme.projectAwesome.controllers;

import jdk.jshell.spi.ExecutionControl;
import justme.projectAwesome.entities.User;
import justme.projectAwesome.exceptions.NotFoundException;
import justme.projectAwesome.exceptions.PasswordsDontMatchException;
import justme.projectAwesome.models.binding.UserRegisterBindingModel;
import justme.projectAwesome.services.interfaces.UserService;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
public class UserController extends BaseController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView loginPage() {
        return this.view("login-page");
    }

    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute UserRegisterBindingModel bindingModel) {
        if (bindingModel.getPassword().equals(
                bindingModel.getConfirmPassword())) {
            this.userService.createUser(bindingModel);
        } else {
            throw new PasswordsDontMatchException("Passwords dont match");
        }
        return this.redirect("/login");
    }

    @GetMapping("/users/userid={userId}")
    public ModelAndView getSingleUser(@PathVariable String userId) {

        Optional<User> userCandidate = this.userService.findById(userId);
        if (!userCandidate.isPresent())
            throw new NotFoundException("There is no such user");
        else
            return this.view("single-user-page", "user", userCandidate.get());
    }
}
