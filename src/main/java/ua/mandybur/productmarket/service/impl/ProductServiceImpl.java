package ua.mandybur.productmarket.service.impl;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.mandybur.productmarket.model.Product;
import ua.mandybur.productmarket.repository.ProductRepository;
import ua.mandybur.productmarket.service.ProductService;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product add(Product product) {
        Optional<Product> sameProduct = productRepository.findByNameAndPrice(product.getName(),
                product.getPrice());
        if (sameProduct.isPresent()) {
            product.setId(sameProduct.get().getId());
            product.setAmount(product.getAmount() + sameProduct.get().getAmount());
        }
        return productRepository.save(product);
    }

    @Override
    public Product get(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Can't get product by id: " + id));
    }

    @Override
    public Product getByName(String name) {
        return productRepository.findByName(name).orElseThrow(() ->
                new EntityNotFoundException("Can't get product by name: " + name));
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAll(Sort sort) {
        return productRepository.findAll(sort);
    }
}
