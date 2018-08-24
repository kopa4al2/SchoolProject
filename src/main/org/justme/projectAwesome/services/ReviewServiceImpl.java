package justme.projectAwesome.services;

import justme.projectAwesome.entities.Comment;
import justme.projectAwesome.entities.Review;
import justme.projectAwesome.models.binding.ReviewBindingModel;
import justme.projectAwesome.repositories.CommentRepository;
import justme.projectAwesome.repositories.ReviewRepository;
import justme.projectAwesome.services.interfaces.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private ModelMapper modelMapper;

    private CommentRepository commentRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository,
                             ModelMapper modelMapper,
                             CommentRepository commentService) {
        this.reviewRepository = reviewRepository;
        this.modelMapper = modelMapper;
        this.commentRepository = commentService;
    }

    @Override
    public Page<Review> findAll(Pageable pageable) {
        return this.reviewRepository.findAll(pageable);
    }

    @Override
    public void createReview(ReviewBindingModel reviewBindingModel) {
        Review r = this.modelMapper.map(reviewBindingModel, Review.class);
        r.setCreatedOn(new Date());

        this.reviewRepository.save(r);

    }

    @Override
    public void deleteReview(String id) {
        Review r = this.reviewRepository.findById(id).get();
        r.setWriter(null);
        for (Comment c : r.getComments()) {
            this.commentRepository.delete(c);
        }
        r.setComments(null);
        this.reviewRepository.delete(r);
    }

    @Override
    public boolean exists(String id) {
        return this.reviewRepository.findById(id).isPresent();
    }

    @Override
    public Review findById(String id) {
        return this.reviewRepository.findById(id).get();
    }

    @Override
    public Page<Review> findAllByTitleContaining(String title, Pageable pageable) {

        return this.reviewRepository.findAllByTitleContaining(title, pageable);
    }

}
