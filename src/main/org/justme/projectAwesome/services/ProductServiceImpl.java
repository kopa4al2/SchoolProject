package justme.projectAwesome.services;

import justme.projectAwesome.entities.Product;
import justme.projectAwesome.entities.Vote;
import justme.projectAwesome.models.binding.ProductEnlistBindingModel;
import justme.projectAwesome.models.binding.VoteBindingModel;
import justme.projectAwesome.repositories.ProductRepository;
import justme.projectAwesome.services.interfaces.ProductService;
import justme.projectAwesome.services.interfaces.VoteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ModelMapper modelMapper;
    private VoteService voteService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              ModelMapper modelMapper,
                              VoteService voteService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.voteService = voteService;
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

    @Override
    public void vote(String productId, VoteBindingModel voteBindingModel) {
        Product product = this.getById(productId);

        Vote vote = this.modelMapper.map(voteBindingModel, Vote.class);
        this.voteService.save(vote);
        product.getVotes().add(vote);
        this.update();
    }

    @Override
    public void update() {
        this.productRepository.flush();
    }
}
