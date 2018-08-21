package justme.projectAwesome.services.interfaces;

import justme.projectAwesome.entities.User;
import justme.projectAwesome.models.binding.UserRegisterBindingModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    boolean createUser(UserRegisterBindingModel user);

    Page<User> getAllByPage(Pageable pageable);

    User findByUsername(String loggedInUsername);

    Optional<User> findById(String userId);

    List<User> findAll();

    void delete(String id);

    void promote(String id);

    void demote(String id);

    Page<User> findAllByUsernameContaining(String username, Pageable pageable);

    Page<User> findAllByEmailContaining(String email, Pageable pageable);

    boolean exists(String id);
}
