package justme.projectAwesome.services.interfaces;

import justme.projectAwesome.entities.Product;
import justme.projectAwesome.models.binding.ProductEnlistBindingModel;
import justme.projectAwesome.models.binding.VoteBindingModel;
import justme.projectAwesome.models.view.ProductViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Page<Product> getAllProductsPageable(Pageable pageable);

    List<Product> getAllProducts();

    void addProduct(ProductEnlistBindingModel bindingModel);

    Product getById(String productId);

    void update();

    boolean exist(String id);
}
