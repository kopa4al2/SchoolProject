package justme.projectAwesome.services.interfaces;

import justme.projectAwesome.entities.UserRole;

public interface UserRoleService {
    boolean contains(String authority);

    UserRole getAuthority(String authority);
}
