package justme.projectAwesome.repositories;

import justme.projectAwesome.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);

//    Set<User> findAllByOrderByIdAsc();

    Page<User> findAllByUsernameContaining(String username, Pageable pageable);

    Page<User> findAllByEmailContaining(String email, Pageable pageable);
}
