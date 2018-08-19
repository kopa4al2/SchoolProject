package justme.projectAwesome.models.view;

import justme.projectAwesome.entities.Category;
import justme.projectAwesome.entities.User;

import java.util.Date;
import java.util.Set;

public class ProductViewModel {

    private String title;

    private double price;

    private String description;

    private Date uploadedOn;

    private String ownerId;

    private Set<String> categoriesNames;

    public ProductViewModel() {
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUploadedOn() {
        return this.uploadedOn;
    }

    public void setUploadedOn(Date uploadedOn) {
        this.uploadedOn = uploadedOn;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Set<String> getCategoriesNames() {
        return this.categoriesNames;
    }

    public void setCategoriesNames(Set<String> categoriesNames) {
        this.categoriesNames = categoriesNames;
    }
}
