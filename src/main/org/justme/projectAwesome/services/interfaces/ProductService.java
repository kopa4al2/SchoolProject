package justme.projectAwesome.services.interfaces;

import justme.projectAwesome.entities.Product;
import justme.projectAwesome.models.binding.ProductEnlistBindingModel;
import justme.projectAwesome.models.binding.VoteBindingModel;

import java.util.List;

public interface ProductService {


    List<Product> getAllProducts();

    void addProduct(ProductEnlistBindingModel bindingModel);

    Product getById(String productId);

    void vote(String productId, VoteBindingModel voteBindingModel);

    void update();
}
