package justme.projectAwesome.repositories;

import justme.projectAwesome.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);

    Set<User> findAllByOrderByIdAsc();
}
