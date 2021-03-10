package ua.mandybur.productmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.mandybur.productmarket.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
