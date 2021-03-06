package justme.projectAwesome.controllers;

import justme.projectAwesome.annotations.ExceptionView;
import justme.projectAwesome.entities.User;
import justme.projectAwesome.services.interfaces.UserService;
import justme.projectAwesome.utils.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AdminController extends BaseController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    @ExceptionView("/admin")
    public ModelAndView getAdminPanel(@PageableDefault(size=20) Pageable pageable) {

        PageWrapper<User> usersPages =
                new PageWrapper<>(this.userService.getAllByPage(pageable), "/admin");
        return this.view("admin-page",
                "pages",
                usersPages);
    }

    @PostMapping(value = "/users/userid={id}")
    @ExceptionView("/admin")
    public ModelAndView changeUserStatus(@PathVariable String id,
                                         @RequestParam(required = false) String promote,
                                         @RequestParam(required = false) String demote) throws Exception {

        if (promote != null)
            this.userService.promote(id);
        else if (demote != null)
            this.userService.demote(id);
        //If it is not promote or demote, its for sure delete
        else
            this.userService.delete(id);

        return this.redirect("/admin");
    }

}
