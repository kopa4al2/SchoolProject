package justme.projectAwesome.entities;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRole implements GrantedAuthority {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private String id;

    private String authority;

    public UserRole() {
    }

    public UserRole(String role) {
        this.authority = role;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }


    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
