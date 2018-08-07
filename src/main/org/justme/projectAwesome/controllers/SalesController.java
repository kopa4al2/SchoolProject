package justme.projectAwesome.controllers;

import justme.projectAwesome.entities.Category;
import justme.projectAwesome.entities.User;
import justme.projectAwesome.models.binding.ProductEnlistBindingModel;
import justme.projectAwesome.services.interfaces.CategorySerice;
import justme.projectAwesome.services.interfaces.ProductService;
import justme.projectAwesome.services.interfaces.UserService;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
public class SalesController {

    private ProductService productService;
    private CategorySerice
            categoryService;
    private UserService userService;

    @Autowired
    public SalesController(ProductService productService,
                           CategorySerice categoryService,
                           UserService userService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping("/sales")
    public ModelAndView allProductsPage(@RequestParam(required = false) String error,
                                  ModelAndView modelAndView) {
        if(error != null) {
            //TODO: Error handling
            throw new NotYetImplementedException(error);
        }
        modelAndView.addObject("products", this.productService.getAllProducts());
        modelAndView.setViewName("sales-page");
        return modelAndView;
    }

    @GetMapping("/sales/sell")
    public ModelAndView sellProductPage(@RequestParam(required = false) String error,
                                  ModelAndView modelAndView) {
        if(error != null) {
            //TODO: Error handling
            throw new NotYetImplementedException(error);
        }
        modelAndView.addObject("categories",
                this.categoryService.getAllCategories());
        modelAndView.setViewName("sell-product");
        return modelAndView;
    }

    @PostMapping("/sales/enlist-product")
    public ModelAndView submitProductForSale(@RequestParam(required = false) String error,
                                 @ModelAttribute ProductEnlistBindingModel bindingModel,
                                 @RequestParam(name = "category")List<String> categories,
                                 ModelAndView modelAndView) {
        if(error != null) {
            throw new NotYetImplementedException(error);
        }
        User loggedInUser = getLoggedInUser();
        bindingModel.setUploadedOn(new Date());
        bindingModel.setOwner(loggedInUser);
        for (String categoryName : categories) {
            Category category = this.categoryService.getCategoryByName(categoryName);
            bindingModel.getCategories().add(category);
        }
        this.productService.addProduct(bindingModel);
        modelAndView.setViewName("redirect:/sales");

        return modelAndView;
    }



    @PostMapping("/sales/add-category")
    public ModelAndView register(@RequestParam(required = false) String error,
                                 @ModelAttribute Category category,
                                 ModelAndView modelAndView) {
        if(error != null) {
            throw new NotYetImplementedException(error);
        }

        if(this.categoryService.getCategory(category) != null) {
            //TODO: Error category already exists
        }
        category.setCreator(this.getLoggedInUser());
        this.categoryService.addCategory(category);

        modelAndView.setViewName("redirect:/sales");

        return modelAndView;
    }

    @GetMapping("/sales/product/id={id}")
    public ModelAndView singleProductPage(ModelAndView modelAndView,
                                          @PathVariable("id") String id) {

        modelAndView.addObject("product", this.productService.getById(id));
        modelAndView.setViewName("single-product-page");
        return modelAndView;
    }


    private User getLoggedInUser() {
        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        return this.userService.findByUsername(loggedInUsername);
    }
}
