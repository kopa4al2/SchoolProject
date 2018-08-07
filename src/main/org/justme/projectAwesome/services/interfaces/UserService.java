package justme.projectAwesome.services.interfaces;

import justme.projectAwesome.entities.User;
import justme.projectAwesome.models.binding.UserRegisterBindingModel;
import justme.projectAwesome.models.binding.VoteBindingModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;
import java.util.Set;

public interface UserService extends UserDetailsService {
    boolean createUser(UserRegisterBindingModel user);

    Set<User> getAllUsers();

    User findByUsername(String loggedInUsername);

    Optional<User> findById(String userId);

    void vote(String userId, VoteBindingModel vote);
}
