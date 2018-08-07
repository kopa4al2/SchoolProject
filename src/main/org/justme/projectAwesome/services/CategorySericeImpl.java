package justme.projectAwesome.services;

import justme.projectAwesome.entities.Category;
import justme.projectAwesome.repositories.CategoryRepository;
import justme.projectAwesome.services.interfaces.CategorySerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorySericeImpl implements CategorySerice {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategorySericeImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return this.categoryRepository.findAll();
    }

    @Override
    public void addCategory(Category category) {
        this.categoryRepository.save(category);
    }

    @Override
    public Category getCategory(Category category) {
        return this.categoryRepository.findByCategoryName(category.getCategoryName());
    }

    @Override
    public Category getCategoryByName(String categoryName) {

        return this.categoryRepository.findByCategoryName(categoryName);
    }


}
