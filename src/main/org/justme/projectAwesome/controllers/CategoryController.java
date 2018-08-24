package justme.projectAwesome.controllers;

import justme.projectAwesome.exceptions.CategoryAlreadyExistsException;
import justme.projectAwesome.models.binding.CategoryBindingModel;
import justme.projectAwesome.services.interfaces.CategoryService;
import justme.projectAwesome.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController extends BaseController{

    private CategoryService categoryService;
    private UserService userService;

    @Autowired
    public CategoryController(CategoryService categoryService,
                              UserService userService) {
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @PostMapping("/sales/add-category")
    public ModelAndView addCategory(@ModelAttribute CategoryBindingModel category) {

        if (this.categoryService.getCategoryByName(category.getCategoryName()) != null) {
            throw new CategoryAlreadyExistsException("Category already exists");
        }
        category.setCreator(this.userService.findByUsername(super.getLoggedInUsername()));
        this.categoryService.addCategory(category);

        return super.redirect("/sales/add-category");
    }
    @PostMapping("/sales/delete-category/{id}")
    public ModelAndView deleteCategory(@PathVariable String id) {

        this.categoryService.deleteById(id);
        return super.redirect("/sales/add-category");
    }

    @GetMapping("/sales/add-category")
    public ModelAndView showCategoryAddForm() {
        return super.view("add-category-page",
                "categories",
                this.categoryService.getAllCategories());
    }


}
