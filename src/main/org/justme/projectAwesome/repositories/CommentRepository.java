package justme.projectAwesome.repositories;

import justme.projectAwesome.entities.Comment;
import justme.projectAwesome.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
}
