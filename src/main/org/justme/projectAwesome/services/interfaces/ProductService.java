package justme.projectAwesome.services.interfaces;

import justme.projectAwesome.entities.Product;
import justme.projectAwesome.models.binding.ProductEnlistBindingModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Page<Product> getAllProductsPageable(Pageable pageable);

    List<Product> getAllProducts();

    void addProduct(ProductEnlistBindingModel bindingModel);

    Product findById(String productId);

    void update();

    Page<Product> findByTitleContaining(String value, Pageable pageable);

    boolean exist(String id);

    void delete(String id);

    Page<Product> findAllByOwner(String id, Pageable pageable);
}
