package justme.projectAwesome.repositories;

import justme.projectAwesome.entities.Product;
import justme.projectAwesome.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    Page<Product> findAllByOwner(User owner, Pageable pageable);

    Page<Product> findAllByTitleContaining(String value, Pageable pageable);
}
