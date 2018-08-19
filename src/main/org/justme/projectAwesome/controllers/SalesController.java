package justme.projectAwesome.controllers;

import justme.projectAwesome.entities.Category;
import justme.projectAwesome.entities.Product;
import justme.projectAwesome.entities.User;
import justme.projectAwesome.exceptions.CategoryAlreadyExistsException;
import justme.projectAwesome.exceptions.InvalidProductException;
import justme.projectAwesome.exceptions.NotFoundException;
import justme.projectAwesome.models.binding.CategoryBindingModel;
import justme.projectAwesome.models.binding.ProductEnlistBindingModel;
import justme.projectAwesome.services.interfaces.CategoryService;
import justme.projectAwesome.services.interfaces.ProductService;
import justme.projectAwesome.services.interfaces.UserService;
import justme.projectAwesome.utils.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
public class SalesController extends BaseController {

    private ProductService productService;
    private CategoryService categoryService;
    private UserService userService;

    @Autowired
    public SalesController(ProductService productService,
                           CategoryService categoryService,
                           UserService userService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping("/sales")
    public ModelAndView allProductsPage(@PageableDefault(size = 10)Pageable pageable) {
        PageWrapper<Product> productPages =
                new PageWrapper<>(this.productService.getAllProductsPageable(pageable), "/sales");

        return this.view("sales-page",
                "products",
                productPages);
    }

    @GetMapping("/sales/sell")
    public ModelAndView sellProductPage() {

        return this.view("sell-product",
                "categories",
                this.categoryService.getAllCategories());
    }

    @PostMapping("/sales/enlist-product")
    public ModelAndView submitProductForSale(@ModelAttribute ProductEnlistBindingModel bindingModel,
                                             @RequestParam(name = "category", required = false) List<String> categories) {

        if (bindingModel.getPrice() < 0)
            throw new InvalidProductException("Price cannot be negative");
        if (bindingModel.getTitle() == null)
            throw new InvalidProductException("Please fill in a title");
        if (categories == null)
            throw new InvalidProductException("Please select atleast 1 category");

        bindingModel.setUploadedOn(new Date());
        bindingModel.setOwner(this.getLoggedInUser());
        for (String categoryName : categories) {
            Category category = this.categoryService.getCategoryByName(categoryName);
            bindingModel.getCategories().add(category);
        }
        this.productService.addProduct(bindingModel);


        return this.redirect("/sales");
    }


    @PostMapping("/sales/add-category")
    public ModelAndView register(@ModelAttribute CategoryBindingModel category) {

        if (this.categoryService.getCategoryByName(category.getCategoryName()) != null) {
            throw new CategoryAlreadyExistsException("Category already exists");
        }
        category.setCreator(this.getLoggedInUser());
        this.categoryService.addCategory(category);

        return this.redirect("/sales/add-category");
    }
    @PostMapping("/sales/delete-category/{id}")
    public ModelAndView deleteCategory(@PathVariable String id) {
        this.categoryService.deleteById(id);
        return this.redirect("/sales/add-category");
    }

    @GetMapping("/sales/add-category")
    public ModelAndView showCategoryAddForm() {
        return this.view("add-category-page",
                "categories",
                this.categoryService.getAllCategories());
    }


    @GetMapping("/sales/product/id={id}")
    public ModelAndView singleProductPage(@PathVariable("id") String id) {

        if (!this.productService.exist(id))
            throw new NotFoundException("No such item in our catalog");

        return this.view("single-product-page",
                "product",
                this.productService.getById(id));
    }


    private User getLoggedInUser() {
        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        return this.userService.findByUsername(loggedInUsername);
    }
}
