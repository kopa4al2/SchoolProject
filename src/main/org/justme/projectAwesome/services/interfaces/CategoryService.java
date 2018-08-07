package justme.projectAwesome.services.interfaces;

import justme.projectAwesome.entities.Category;
import justme.projectAwesome.repositories.CategoryRepository;

public interface CategoryService {

    boolean contains(String categoryName);

    Category getCategoryByName(String categoryName);

}
