package justme.projectAwesome.repositories;

import justme.projectAwesome.entities.Product;
import justme.projectAwesome.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {


    List<Product> findAllByOwner(User owner);
}
