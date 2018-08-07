package justme.projectAwesome.services;

import justme.projectAwesome.entities.Comment;
import justme.projectAwesome.repositories.CommentRepository;
import justme.projectAwesome.services.interfaces.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void save(Comment comment) {
        this.commentRepository.save(comment);
    }
}
