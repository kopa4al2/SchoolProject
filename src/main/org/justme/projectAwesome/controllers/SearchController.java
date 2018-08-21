package justme.projectAwesome.controllers;

import justme.projectAwesome.entities.Product;
import justme.projectAwesome.entities.User;
import justme.projectAwesome.services.interfaces.ProductService;
import justme.projectAwesome.services.interfaces.UserService;
import justme.projectAwesome.utils.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SearchController extends BaseController {

    private ProductService productService;
    private UserService userService;

    @Autowired
    public SearchController(ProductService productService,
                            UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping(value = "/admin/search/")
    public ModelAndView filterUsers(Pageable pageable,
                                    @RequestParam(required = false) String username,
                                    @RequestParam(required = false) String email) {
        PageWrapper<User> wrapper = null;
        if (username != null)
            wrapper = new PageWrapper<>(
                    this.userService.findAllByUsernameContaining(username, pageable),
                    "/admin/search/?username=" + username);
        else if (email != null)
            wrapper = new PageWrapper<>(
                    this.userService.findAllByEmailContaining(email, pageable),
                    "/admin/search/?username=" + email);
        return this.view("admin-page::all-users-table", "pages", wrapper);
    }

    @GetMapping(value = "/sales/get-all/search/")
    public ModelAndView filterProductsByTitle(@RequestParam String title, Pageable pageable) {
        PageWrapper<Product> wrapper =
                new PageWrapper<>(this.productService.findByTitleContaining(title, pageable),
                        "sales/get-all/search/?title=" + title);

        return this.view("sales-page::products-container-fragment", "products", wrapper);
    }

}
