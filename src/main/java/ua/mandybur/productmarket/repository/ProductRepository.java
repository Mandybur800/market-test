package ua.mandybur.productmarket.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.mandybur.productmarket.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);

    Optional<Product> findByNameAndPrice(String name, double price);
}
