package justme.projectAwesome.services;

import justme.projectAwesome.entities.Comment;
import justme.projectAwesome.entities.interfaces.Commentable;
import justme.projectAwesome.models.binding.CommentBindingModel;
import justme.projectAwesome.repositories.CommentRepository;
import justme.projectAwesome.services.interfaces.CommentService;
import justme.projectAwesome.services.interfaces.ProductService;
import justme.projectAwesome.services.interfaces.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    private ModelMapper modelMapper;
    private ProductService productService;
    private ReviewService reviewService;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository,
                              ModelMapper modelMapper,
                              ProductService productSerice,
                              ReviewService reviewService) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
        this.productService = productSerice;
        this.reviewService = reviewService;
    }

    @Override
    public void save(CommentBindingModel comment) {
        this.commentRepository.save(this.modelMapper.map(comment, Comment.class));
    }

    @Override
    public void delete(Comment c) {
        this.commentRepository.delete(c);
    }

    @Override
    public void delete(String id) {
        this.commentRepository.deleteById(id);
    }


    @Override
    public void comment(String id, CommentBindingModel commentBindingModel, Commentable entityToBeCommented) {
            Comment c = this.modelMapper.map(commentBindingModel, Comment.class);
            entityToBeCommented.addComment(c);
            this.commentRepository.save(c);
    }
}
