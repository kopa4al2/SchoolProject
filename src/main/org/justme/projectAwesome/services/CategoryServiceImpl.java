package justme.projectAwesome.services;

import justme.projectAwesome.entities.Category;
import justme.projectAwesome.repositories.CategoryRepository;
import justme.projectAwesome.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean contains(String categoryName) {
        return this.categoryRepository.findByCategoryName(categoryName) != null;
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        return this.categoryRepository.findByCategoryName(categoryName);
    }
}
