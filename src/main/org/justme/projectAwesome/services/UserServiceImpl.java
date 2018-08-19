package justme.projectAwesome.services;

import justme.projectAwesome.entities.Product;
import justme.projectAwesome.entities.User;
import justme.projectAwesome.entities.UserRole;
import justme.projectAwesome.models.binding.UserRegisterBindingModel;
import justme.projectAwesome.repositories.ProductRepository;
import justme.projectAwesome.repositories.UserRepository;
import justme.projectAwesome.services.interfaces.UserRoleService;
import justme.projectAwesome.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserRoleService userRoleService;

    private final ModelMapper modelMapper;

    private final BCryptPasswordEncoder passwordEncoder;

    private final ProductRepository productRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper,
                           UserRoleService userRoleService,
                           BCryptPasswordEncoder passwordEncoder,
                           ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.userRoleService = userRoleService;
        this.passwordEncoder = passwordEncoder;
        this.productRepository = productRepository;
    }

    @Override
    public boolean createUser(UserRegisterBindingModel user) {
        User userEntity = this.modelMapper.map(user, User.class);

        userEntity.setPassword(this.passwordEncoder.encode(userEntity.getPassword()));

        UserRole roleUser;
        if (!this.userRoleService.contains("USER"))
            roleUser = new UserRole("USER");
        else
            roleUser = this.userRoleService.getAuthority("USER");
        userEntity.addAuthority(roleUser);
        if (this.userRepository.findAll().isEmpty()) {
            UserRole roleOwner = new UserRole("OWNER");
            UserRole roleAdmin = new UserRole("ADMIN");
            UserRole roleModerator = new UserRole("MODERATOR");
            userEntity.addAuthority(roleAdmin);
            userEntity.addAuthority(roleModerator);
            userEntity.addAuthority(roleOwner);
        }

        this.userRepository.save(userEntity);
        return true;
    }

    @Override
    public Page<User> getAllByPage(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    @Override
    public Set<User> getAllUsersOrderedById() {
        return this.userRepository.findAllByOrderByIdAsc();
    }

    @Override
    public Set<User> getAllUsers() {
        return this.userRepository
                .findAll()
                .stream()
                .collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public User findByUsername(String loggedInUsername) {
        return this.userRepository.findByUsername(loggedInUsername);
    }

    @Override
    public Optional<User> findById(String userId) {
        return this.userRepository.findById(userId);
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }
    @Override
    public void delete(String id) {
        User u = this.userRepository.findById(id).get();
        u.setAuthorities(new HashSet<>());
        for (Product product : this.productRepository.findAllByOwner(u)) {
            this.productRepository.delete(product);
        }
        this.userRepository.delete(this.userRepository.findById(id).get());
    }

    @Override
    public void demote(String id) {
        User u = this.userRepository.findById(id).get();
        if(u.getAuthorities().contains(getAuthority("OWNER")))
            //TODO: maybe throw an exception
            return;
        if (u.getAuthorities().contains(getAuthority("ADMIN"))){
            u.removeAuthority(getAuthority("ADMIN"));
        } else if(u.getAuthorities().contains(getAuthority("MODERATOR"))) {
            u.removeAuthority(getAuthority("MODERATOR"));
        }
        this.userRepository.saveAndFlush(u);
    }

    @Override
    public void promote(String id) {
        User u = this.userRepository.findById(id).get();
        if (!u.isAdmin() && u.getAuthorities().contains(getAuthority("MODERATOR"))){
            u.addAuthority(getAuthority("ADMIN"));
        } else if(!u.isAdmin() && !u.getAuthorities().contains(getAuthority("MODERATOR"))) {
            u.addAuthority(getAuthority("MODERATOR"));
        } else if(u.isAdmin())
            //TODO: maybe throw an exception
            return;
        this.userRepository.saveAndFlush(u);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    private UserRole getAuthority(String authority) {
        return this.userRoleService.getAuthority(authority);
    }

}
