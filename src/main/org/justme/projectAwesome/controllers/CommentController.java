package justme.projectAwesome.controllers;

import justme.projectAwesome.exceptions.NotFoundException;
import justme.projectAwesome.models.binding.CommentBindingModel;
import justme.projectAwesome.services.interfaces.CommentService;
import justme.projectAwesome.services.interfaces.ProductService;
import justme.projectAwesome.services.interfaces.ReviewService;
import justme.projectAwesome.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class CommentController extends BaseController {
    private static final String TOBE_COMMENTED_ENTITY_PRODUCT = "product";
    private static final String TOBE_COMMENTED_ENTITY_REVIEW = "review";

    private CommentService commentService;
    private UserService userService;
    private ProductService productService;
    private ReviewService reviewSerice;

    @Autowired
    public CommentController(CommentService commentService,
                             UserService userService,
                             ProductService productService,
                             ReviewService reviewSerice) {
        this.commentService = commentService;
        this.userService = userService;
        this.productService = productService;
        this.reviewSerice = reviewSerice;
    }

    @PostMapping("/product/comment/{productId}")
    public ModelAndView commentProduct(@PathVariable String productId,
                                      @ModelAttribute CommentBindingModel commentBindingModel) {


        if(!this.productService.exist(productId))
            throw new NotFoundException("There is no product to comment");
        commentBindingModel.setWriter(this.userService.findByUsername(super.getLoggedInUsername()));
        commentBindingModel.setCreatedOn(new Date());
        this.commentService.comment(productId, commentBindingModel, this.productService.findById(productId));

        return super.redirect("/sales/product/id=" + productId);
    }

    @PostMapping("/reviews/comment/{reviewId}")
    public ModelAndView commentReview(@PathVariable String reviewId,
                                       @ModelAttribute CommentBindingModel commentBindingModel) {

        commentBindingModel.setWriter(this.userService.findByUsername(super.getLoggedInUsername()));
        this.commentService.comment(reviewId, commentBindingModel, this.reviewSerice.findById(reviewId));

       return super.redirect("/sales/product/id=" + reviewId);
    }


}
