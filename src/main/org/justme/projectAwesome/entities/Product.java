package justme.projectAwesome.entities;

import justme.projectAwesome.entities.enums.VoteType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private String id;

    @ManyToOne
    private User owner;

    @Column(nullable = false)
    private Double price;

    @Column
    private String description;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, updatable = false)
    private Date uploadedOn;

    @ManyToMany
    private List<Comment> comments;

    @ElementCollection
    @CollectionTable(name="img_urls", joinColumns=@JoinColumn(name="product_id"))
    @OrderColumn
    private Set<String> imgsUrl;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Category> categories;

    public Product() {

    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getOwner() {
        return this.owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getUploadedOn() {
        return this.uploadedOn;
    }

    public void setUploadedOn(Date uploadedOn) {
        this.uploadedOn = uploadedOn;
    }

    public Set<String> getImgsUrl() {
        return this.imgsUrl;
    }

    public void setImgsUrl(Set<String> imgsUrl) {
        this.imgsUrl = imgsUrl;
    }

    public Set<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Comment> getComments() {
        return this.comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}

