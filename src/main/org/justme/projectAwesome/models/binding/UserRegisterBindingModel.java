package justme.projectAwesome.models.binding;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegisterBindingModel {

    @Size(min=3, max=16, message = "username must be between 3 and 16 symbols")
    @NotNull(message = "username cannot be null")
    private String username;

    @Size(min=6, message = "Password must be atleast 6 symbols")
    @NotNull(message = "Password cannot be null")
    private String password;

    @NotNull(message = "Confirm password cant be null")
    private String confirmPassword;

    @Email(message = "Invalid email")
    @NotNull(message = "Email cannot be null")
    private String email;

    public UserRegisterBindingModel() {
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
