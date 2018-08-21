package justme.projectAwesome.models.binding;

import justme.projectAwesome.entities.Category;
import justme.projectAwesome.entities.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ProductEnlistBindingModel {

    private String title;

    private double price;

    private String description;

    private Date uploadedOn;

    private User owner;

    private Set<Category> categories;
    private Set<String> imgsUrl;

    public ProductEnlistBindingModel() {
        this.categories = new HashSet<>();
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getUploadedOn() {
        return this.uploadedOn;
    }

    public void setUploadedOn(Date uploadedOn) {
        this.uploadedOn = uploadedOn;
    }

    public User getOwner() {
        return this.owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<Category> getCategories() {
        if(this.categories == null)
            return new HashSet<>();
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

    public void setImgsUrl(Set<String> imgsUrl) {
        this.imgsUrl = imgsUrl;
    }

    public Set<String> getImgsUrl() {
        return imgsUrl;
    }
}
