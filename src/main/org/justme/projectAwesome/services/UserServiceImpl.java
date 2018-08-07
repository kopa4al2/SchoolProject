package justme.projectAwesome.services;

import justme.projectAwesome.entities.User;
import justme.projectAwesome.entities.UserRole;
import justme.projectAwesome.entities.Vote;
import justme.projectAwesome.models.binding.UserRegisterBindingModel;
import justme.projectAwesome.models.binding.VoteBindingModel;
import justme.projectAwesome.repositories.UserRepository;
import justme.projectAwesome.repositories.VoteRepository;
import justme.projectAwesome.services.interfaces.UserRoleService;
import justme.projectAwesome.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final VoteRepository voteRepository;

    private final UserRoleService userRoleService;

    private final ModelMapper modelMapper;

    private final BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           VoteRepository voteRepository,
                           ModelMapper modelMapper,
                           UserRoleService userRoleService,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
        this.modelMapper = modelMapper;
        this.userRoleService = userRoleService;
        this.passwordEncoder = passwordEncoder;
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
            UserRole roleModerator = new UserRole("MODERATOR");
            userEntity.addAuthority(roleOwner);
            userEntity.addAuthority(roleModerator);
        }

        this.userRepository.save(userEntity);
        return true;
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
    public void vote(String userId, VoteBindingModel vote) {
        if (this.userRepository.findById(userId).isPresent()) {
            this.userRepository.findById(userId)
                    .get()
                    .getVotes()
                    .add(this.modelMapper.map(vote, Vote.class));
//            this.voteRepository.flush();
            this.userRepository.flush();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No such user");
        }
        return user;
    }

}
