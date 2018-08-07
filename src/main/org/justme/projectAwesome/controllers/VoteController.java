package justme.projectAwesome.controllers;

import justme.projectAwesome.entities.Comment;
import justme.projectAwesome.entities.User;
import justme.projectAwesome.entities.Vote;
import justme.projectAwesome.models.binding.VoteBindingModel;
import justme.projectAwesome.entities.enums.VoteType;
import justme.projectAwesome.services.interfaces.ProductService;
import justme.projectAwesome.services.interfaces.ReviewService;
import justme.projectAwesome.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.InvalidParameterException;
import java.security.spec.InvalidParameterSpecException;

@Controller
public class VoteController {

    private ProductService productService;
    private UserService userService;
    private ReviewService reviewService;

    @Autowired
    public VoteController(ProductService productService,
                          UserService userService,
                          ReviewService reviewService) {
        this.productService = productService;
        this.userService = userService;
        this.reviewService = reviewService;
    }

    @PostMapping(value = "/sales/product/{productId}/vote")
    public ModelAndView voteProduct(ModelAndView modelAndView,
                                    @PathVariable String productId,
                                    @ModelAttribute Comment comment,
                                    @RequestParam(value = "upvote", required = false) String upvote,
                                    @RequestParam(value = "downvote", required = false) String downvote) {

        VoteBindingModel vote = getVoteBindingModel(comment, upvote, downvote);

        this.productService.vote(productId, vote);
        modelAndView.setViewName("redirect:/sales/product/id=" + productId);
        return modelAndView;
    }


    @PostMapping(value = "/users/{userId}/vote")
    public ModelAndView voteUser(ModelAndView modelAndView,
                                 @PathVariable String userId,
                                 @ModelAttribute Comment comment,
                                 @RequestParam(value = "upvote", required = false) String upvote,
                                 @RequestParam(value = "downvote", required = false) String downvote) {

        VoteBindingModel vote = getVoteBindingModel(comment, upvote, downvote);

        this.userService.vote(userId, vote);
        modelAndView.setViewName("redirect:/users/userid=" + userId);
        return modelAndView;
    }

    private VoteBindingModel getVoteBindingModel(Comment comment, @RequestParam(value = "upvote", required = false) String upvote, @RequestParam(value = "downvote", required = false) String downvote) {
        VoteBindingModel vote;
        if (upvote != null) {
            vote = processVote(comment, VoteType.UpVote);
        } else if (downvote != null) {
            vote = processVote(comment, VoteType.DownVote);
        } else {
            throw new InvalidParameterException("No downvote or upvote, are you trying something funny?");
        }
        return vote;
    }

    private VoteBindingModel processVote(Comment comment, VoteType voteType) {
        VoteBindingModel voteBindingModel = new VoteBindingModel();
        comment.setWriter(this.getLoggedInUser());
        voteBindingModel.setVoteContent(comment);
        voteBindingModel.setVoteType(voteType);
        voteBindingModel.setVoteOwner(this.getLoggedInUser());
        return voteBindingModel;
    }

    private User getLoggedInUser() {
        String loggedInUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        return this.userService.findByUsername(loggedInUsername);
    }
}
