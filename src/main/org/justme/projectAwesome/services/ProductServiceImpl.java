package justme.projectAwesome.services;

import justme.projectAwesome.entities.Product;
import justme.projectAwesome.models.binding.ProductEnlistBindingModel;
import justme.projectAwesome.repositories.CommentRepository;
import justme.projectAwesome.repositories.ProductRepository;
import justme.projectAwesome.repositories.UserRepository;
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
    private CommentRepository commentRepository;
    private ModelMapper modelMapper;
    private UserRepository userRepository;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              CommentRepository commentRepository,
                              ModelMapper modelMapper,
                              UserRepository userRepository) {
        this.productRepository = productRepository;
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
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
        Product p = this.modelMapper.map(bindingModel, Product.class);
        this.productRepository.save(p);
    }

    @Override
    public Product findById(String productId) {
        return this.productRepository.findById(productId).get();
    }

    public boolean exist(String productId) {
        Optional<Product> p = this.productRepository.findById(productId);
        if(p.isPresent())
            return true;
        return false;
    }

    @Override
    public void delete(String id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public Page<Product> findAllByOwner(String id, Pageable pageable) {
        return this.productRepository.findAllByOwner(
                this.userRepository.findById(id).get(),
                pageable);
    }

    @Override
    public void update() {
        this.productRepository.flush();
    }

    @Override
    public Page<Product> findByTitleContaining(String value, Pageable pageable) {
        return this.productRepository.findAllByTitleContaining(value, pageable);
    }
}
