package justme.projectAwesome.models.binding;

import justme.projectAwesome.entities.User;

public class CategoryBindingModel {

    private String categoryName;

    private User creator;

    public CategoryBindingModel() {

    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public User getCreator() {
        return this.creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
}
