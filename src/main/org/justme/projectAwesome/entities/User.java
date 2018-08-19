package justme.projectAwesome.entities;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private String id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column
    private String email;

    @Column
    private String profilePictureUrl;

    @Column
    private String firstName;

    @Column
    private String surName;

    @Column
    private String lastName;

    @Column
    private int age;

    @Column
    private String city;

    @Column
    private String country;

    @Column
    private String phoneNumber;

    @Column
    private Double balance = 0D;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<UserRole> authorities;

    @ManyToMany
    private List<User> friends;

    @ManyToMany
    private List<Product> productsInSale;

    @ManyToMany
    private List<Product> productsSoldLastWeek;

    @ManyToMany
    private List<Product> productsBoughtLastWeek;

    @ManyToMany
    private List<Comment> comments;

    @ManyToMany
    private List<Notification> notifications;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.authorities == null)
            return new HashSet<>();
        return this.authorities;
    }

    public void setAuthorities(Set<UserRole> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isEnabled() {
        return true;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addAuthority(UserRole authority) {

        if (this.authorities == null)
            this.authorities = new HashSet<>();
        this.authorities.add(authority);
    }

    public void removeAuthority(UserRole authority) {
        if(this.authorities == null)
            return;
        this.authorities.remove(authority);
    }

    public String getProfilePictureUrl() {
        return this.profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public List<User> getFriends() {
        return this.friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public List<Product> getProductsInSale() {
        return this.productsInSale;
    }

    public void setProductsInSale(List<Product> productsInSale) {
        this.productsInSale = productsInSale;
    }

    public List<Product> getProductsSoldLastWeek() {
        return this.productsSoldLastWeek;
    }

    public void setProductsSoldLastWeek(List<Product> productsSoldLastWeek) {
        this.productsSoldLastWeek = productsSoldLastWeek;
    }

    public List<Product> getProductsBoughtLastWeek() {
        return this.productsBoughtLastWeek;
    }

    public void setProductsBoughtLastWeek(List<Product> productsBoughtLastWeek) {
        this.productsBoughtLastWeek = productsBoughtLastWeek;
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getBalance() {
        return this.balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<Notification> getNotifications() {
        return this.notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return this.surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return this.authorities.contains(new UserRole("ADMIN"));
    }

    public boolean isModerator() {
        return this.authorities.contains(new UserRole("MODERATOR"));
    }

    public boolean isUser() {
        return !this.authorities.contains(new UserRole("MODERATOR")) &&
               !this.isAdmin();
    }
}
