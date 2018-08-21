package justme.projectAwesome.controllers;

import justme.projectAwesome.entities.Product;
import justme.projectAwesome.exceptions.NotFoundException;
import justme.projectAwesome.services.interfaces.ProductService;
import justme.projectAwesome.services.interfaces.UserService;
import justme.projectAwesome.utils.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserInfoController extends BaseController{


    private ProductService productService;
    private UserService userService;

    @Autowired
    public UserInfoController(ProductService productService,
                              UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("users/info/items-for-sale/userid={id}")
    public ModelAndView getProductsForSaleFromUser(@PathVariable String id,
                                                   Pageable pageable) {

        if(!this.userService.exists(id)) {
            throw new NotFoundException("The user whose products you are looking for doesnt exist");
        }
        PageWrapper<Product> products = new PageWrapper<>(
                this.productService.findAllByOwner(id, pageable),
                "/users/info/items-for-sale/userid={id}"
        );
        return this.view("single-user-page::user-info-fragment", "productsForSale", products);
    }
}
