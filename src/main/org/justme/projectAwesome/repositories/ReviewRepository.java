package justme.projectAwesome.repositories;

import justme.projectAwesome.entities.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {

    Page<Review> findAllByTitleContaining(String title, Pageable pageable);
}
