package justme.projectAwesome.services.interfaces;

import justme.projectAwesome.entities.Category;
import justme.projectAwesome.models.binding.CategoryBindingModel;
import justme.projectAwesome.repositories.CategoryRepository;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    void addCategory(CategoryBindingModel category);

    Category getCategory(Category category);

    Category getCategoryByName(String categoryName);

    boolean contains(String categoryName);

    void deleteById(String id);

    void findById(String id);
}
