package justme.projectAwesome.services.interfaces;

import justme.projectAwesome.entities.Category;

import java.util.List;

public interface CategorySerice {

    List<Category> getAllCategories();

    void addCategory(Category category);

    Category getCategory(Category category);

    Category getCategoryByName(String categoryName);
}
