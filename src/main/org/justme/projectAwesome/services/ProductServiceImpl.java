package justme.projectAwesome.services;

import justme.projectAwesome.entities.Product;
import justme.projectAwesome.models.binding.ProductEnlistBindingModel;
import justme.projectAwesome.repositories.ProductRepository;
import justme.projectAwesome.services.interfaces.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<Product> getAllProductsPageable(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public void addProduct(ProductEnlistBindingModel bindingModel) {
        this.productRepository.save(this.modelMapper.map(bindingModel, Product.class));
    }

    @Override
    public Product getById(String productId) {
        return this.productRepository.getOne(productId);
    }

    public boolean exist(String productId) {
        Optional<Product> p = this.productRepository.findById(productId);
        if(p.isPresent())
            return true;
        return false;
    }

    @Override
    public void update() {
        this.productRepository.flush();
    }
}
