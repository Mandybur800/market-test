package ua.mandybur.productmarket.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import ua.mandybur.productmarket.model.Product;

public interface ProductService {
    Product add(Product product);

    Product get(Long id);

    Product getByName(String name);

    Product update(Product product);

    List<Product> getAll(Sort sort);
}
