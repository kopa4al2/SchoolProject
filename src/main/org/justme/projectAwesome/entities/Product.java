package justme.projectAwesome.entities;

import justme.projectAwesome.entities.interfaces.Commentable;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product implements Commentable {

    private static final int DESCRIPTION_MAX_LENGTH = 1000;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private String id;

    @ManyToOne
    private User owner;

    @Column(nullable = false)
    private Double price;

    @Column(length = DESCRIPTION_MAX_LENGTH)
    private String description;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, updatable = false)
    private Date uploadedOn;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ElementCollection
    @CollectionTable(name="img_urls", joinColumns=@JoinColumn(name="product_id"))
    @OrderColumn
    private Set<String> imgsUrl;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.EAGER)
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
        if(this.comments == null)
            return new ArrayList<>();
        return this.comments;
    }

    @Override
    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}

