package justme.projectAwesome.services;

import justme.projectAwesome.entities.UserRole;
import justme.projectAwesome.repositories.UserRoleRepository;
import justme.projectAwesome.services.interfaces.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    private UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public boolean contains(String authority) {
        return this.userRoleRepository.findByAuthority(authority) != null;
    }

    @Override
    public UserRole getAuthority(String authority) {
        return this.userRoleRepository.findByAuthority(authority);
    }
}
