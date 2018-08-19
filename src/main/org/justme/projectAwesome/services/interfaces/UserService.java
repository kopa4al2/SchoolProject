package justme.projectAwesome.services.interfaces;

import justme.projectAwesome.entities.User;
import justme.projectAwesome.models.binding.UserRegisterBindingModel;
import justme.projectAwesome.models.binding.VoteBindingModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService extends UserDetailsService {

    boolean createUser(UserRegisterBindingModel user);

    Page<User> getAllByPage(Pageable pageable);

    Set<User> getAllUsersOrderedById();

    Set<User> getAllUsers();

    User findByUsername(String loggedInUsername);

    Optional<User> findById(String userId);

    List<User> findAll();

    void delete(String id);

    void promote(String id);

    void demote(String id);
}
