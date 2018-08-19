package justme.projectAwesome.services;

import justme.projectAwesome.entities.Category;
import justme.projectAwesome.models.binding.CategoryBindingModel;
import justme.projectAwesome.repositories.CategoryRepository;
import justme.projectAwesome.services.interfaces.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorySericeImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    private ModelMapper modelMapper;

    @Autowired
    public CategorySericeImpl(CategoryRepository categoryRepository,
                              ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Category> getAllCategories() {
        return this.categoryRepository.findAll();
    }

    @Override
    public void addCategory(CategoryBindingModel category) {
        this.categoryRepository.save(this.modelMapper.map(category, Category.class));
    }

    @Override
    public Category getCategory(Category category) {
        return this.categoryRepository.findByCategoryName(category.getCategoryName());
    }

    @Override
    public boolean contains(String categoryName) {
        return this.categoryRepository.findByCategoryName(categoryName) != null;
    }

    @Override
    public void deleteById(String id) {
        this.categoryRepository.deleteById(id);
    }

    @Override
    public void findById(String id) {
        this.categoryRepository.findById(id);
    }

    @Override
    public Category getCategoryByName(String categoryName) {

        return this.categoryRepository.findByCategoryName(categoryName);
    }


}
