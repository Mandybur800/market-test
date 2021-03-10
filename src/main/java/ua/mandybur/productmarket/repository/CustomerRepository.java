package ua.mandybur.productmarket.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.mandybur.productmarket.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("FROM customers c JOIN FETCH c.roles r WHERE c.email = :email")
    Optional<Customer> findByEmail(String email);
}
