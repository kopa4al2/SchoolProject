package justme.projectAwesome.services.interfaces;

import justme.projectAwesome.entities.Product;
import justme.projectAwesome.entities.Review;
import justme.projectAwesome.models.binding.ReviewBindingModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
    Page<Review> findAll(Pageable pageable);

    void createReview(ReviewBindingModel reviewBindingModel);

    void deleteReview(String id);

    boolean exists(String id);

    Review findById(String id);

}
