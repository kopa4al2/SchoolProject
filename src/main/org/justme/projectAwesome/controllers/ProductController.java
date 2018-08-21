package justme.projectAwesome.controllers;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import justme.projectAwesome.entities.Category;
import justme.projectAwesome.entities.Product;
import justme.projectAwesome.exceptions.InvalidProductException;
import justme.projectAwesome.exceptions.NotFoundException;
import justme.projectAwesome.models.binding.ProductEnlistBindingModel;
import justme.projectAwesome.services.interfaces.CategoryService;
import justme.projectAwesome.services.interfaces.ProductService;
import justme.projectAwesome.services.interfaces.UserService;
import justme.projectAwesome.utils.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.*;

@Controller
public class ProductController extends BaseController {

    private static final String CLOUDINARY_API_KEY = "516829359226793";
    private static final String CLOUDINARY_API_SECRET = "wLPI5A00m65ZC7vzch6Bx3x4D4c";
    private static final String CLOUDINARY_API_CLOUD_NAME = "durmddeoj";

    private ProductService productService;
    private CategoryService categoryService;
    private UserService userService;
    private Cloudinary cloudinary;

    @Autowired
    public ProductController(ProductService productService,
                             CategoryService categoryService,
                             UserService userService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.cloudinary = new Cloudinary("cloudinary://"+CLOUDINARY_API_KEY+":"+CLOUDINARY_API_SECRET+"@"+CLOUDINARY_API_CLOUD_NAME);
    }
    @GetMapping("/sales")
    public ModelAndView allProductsPage(@PageableDefault(size = 10)Pageable pageable) {


        PageWrapper<Product> productPages =
                new PageWrapper<>(
                        this.productService.getAllProductsPageable(pageable),
                        "/sales");

        return super.view("sales-page",
                "products",
                productPages);
    }

    @GetMapping("/sales/sell")
    public ModelAndView sellProductPage() {

        return super.view("sell-product",
                "categories",
                this.categoryService.getAllCategories());
    }

    @PostMapping("/sales/enlist-product")
    public ModelAndView submitProductForSale(@ModelAttribute ProductEnlistBindingModel bindingModel,
                                             MultipartHttpServletRequest request,
                                             @RequestParam(name = "category", required = false) List<String> categories) throws IOException {

        Map uploadResult = null;
        Set<String> uploadedImagesUrls = new HashSet<>();

        Iterator<String> fileNames = request.getFileNames();
        while(fileNames.hasNext()) {
            String fileName = fileNames.next();
            uploadResult = cloudinary.uploader().upload(request.getFile(fileName).getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
            uploadedImagesUrls.add((String) uploadResult.get("secure_url"));
        }


        if (bindingModel.getPrice() < 0)
            throw new InvalidProductException("Price cannot be negative");
        if (bindingModel.getTitle() == null)
            throw new InvalidProductException("Please fill in a title");
        if (categories == null)
            throw new InvalidProductException("Please select atleast 1 category");

        bindingModel.setUploadedOn(new Date());
        bindingModel.setOwner(this.userService.findByUsername(super.getLoggedInUsername()));
        bindingModel.setImgsUrl(uploadedImagesUrls);
        for (String categoryName : categories) {
            Category category = this.categoryService.getCategoryByName(categoryName);
            bindingModel.getCategories().add(category);
        }
        this.productService.addProduct(bindingModel);


        return super.redirect("/sales");
    }

    @GetMapping("/sales/product/id={id}")
    public ModelAndView singleProductPage(@PathVariable String id) {

        if (!this.productService.exist(id))
            throw new NotFoundException("No such item in our catalog");

        return super.view("single-product-page",
                "product",
                this.productService.findById(id));
    }

    @PostMapping("/sales/product/delete/id={id}")
    public ModelAndView deleteProduct(@PathVariable String id) {
        if(!this.productService.exist(id))
            throw new NotFoundException("No such item in our catalog");

        this.productService.delete(id);

        return super.redirect("/");
    }
}
