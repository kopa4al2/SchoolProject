package justme.projectAwesome.controllers;

import justme.projectAwesome.entities.User;
import justme.projectAwesome.exceptions.NotFoundException;
import justme.projectAwesome.exceptions.PasswordsDontMatchException;
import justme.projectAwesome.models.binding.UserRegisterBindingModel;
import justme.projectAwesome.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

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
    public ModelAndView register(@Valid @ModelAttribute UserRegisterBindingModel bindingModel,
                                 BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
           return this.view("errors/error", "bindingResult", bindingResult);
        }
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

    @PostMapping("/users/edit/userid={userId}")
    public ModelAndView editUser(@PathVariable String userId,
                                 MultipartHttpServletRequest image) throws Exception {

        if(image.getFileMap().size() > 1) {
            throw new Exception("Upload only one photo");
        }
        Set<String> profileImg = super.uploadToCloudinary(image);
        String url = (String) profileImg.stream().toArray()[0];
        User u = this.userService.findById(userId).get();
        u.setProfilePictureUrl(url);
        this.userService.update(u);


        return this.view("single-user-page", "user", u);
    }
}
