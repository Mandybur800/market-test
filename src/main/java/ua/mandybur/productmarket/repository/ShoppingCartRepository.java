package ua.mandybur.productmarket.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.mandybur.productmarket.model.Customer;
import ua.mandybur.productmarket.model.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    @Query("FROM shopping_carts s JOIN FETCH s.customer "
            + "LEFT JOIN FETCH s.addedItems WHERE s.customer = ?1")
    Optional<ShoppingCart> getByCustomer(Customer customer);
}
