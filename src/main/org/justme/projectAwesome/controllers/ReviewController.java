package justme.projectAwesome.controllers;

import justme.projectAwesome.entities.Review;
import justme.projectAwesome.models.binding.ReviewBindingModel;
import justme.projectAwesome.services.interfaces.ReviewService;
import justme.projectAwesome.services.interfaces.UserService;
import justme.projectAwesome.utils.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReviewController extends BaseController{

    private ReviewService reviewService;
    private UserService userService;

    @Autowired
    public ReviewController(ReviewService reviewService,
                            UserService userService) {
        this.reviewService = reviewService;
        this.userService = userService;
    }

    @GetMapping("/reviews")
    public ModelAndView showReviewsPage(@PageableDefault(size=10) Pageable pageable) {
        PageWrapper<Review> reviewsPages =
                new PageWrapper<>(this.reviewService.findAll(pageable), "/reviews");

        return super.view("reviews-page", "reviewsPages", reviewsPages);
    }

    @PostMapping("/reviews/create/")
    public ModelAndView createReview(@ModelAttribute ReviewBindingModel reviewBindingModel){
        reviewBindingModel.setWriter(this.userService.findByUsername(super.getLoggedInUsername()));
        this.reviewService.createReview(reviewBindingModel);

        return super.redirect("/reviews");
    }

    @PostMapping("/reviews/delete/reviewId={id}")
    public ModelAndView deleteReview(@PathVariable String id) {

//        if(!this.reviewService.exists(id))
//            throw new NotFoundException("There is no such review");
//        this.reviewService.deleteReview(id);

        return super.view("reviews-page");
//        return super.redirect("/reviews", "redirectUrl", "/reviews");
    }

    @GetMapping("reviews/reviewId={reviewId}")
    public ModelAndView getSingleReviewPage(@PathVariable String reviewId) {
        return super.view("single-review-page", "review", this.reviewService.findById(reviewId));
    }

}
