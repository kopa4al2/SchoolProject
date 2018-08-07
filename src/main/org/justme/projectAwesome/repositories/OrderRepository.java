package justme.projectAwesome.repositories;

import justme.projectAwesome.entities.Comment;
import justme.projectAwesome.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
}
