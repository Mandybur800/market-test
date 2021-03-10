package ua.mandybur.productmarket.service.impl;

import java.util.HashMap;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.mandybur.productmarket.model.Order;
import ua.mandybur.productmarket.model.ShoppingCart;
import ua.mandybur.productmarket.repository.OrderRepository;
import ua.mandybur.productmarket.service.OrderService;
import ua.mandybur.productmarket.service.ProductService;
import ua.mandybur.productmarket.service.ShoppingCartService;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ShoppingCartService shoppingCartService;
    private final OrderRepository orderRepository;
    private final ProductService productService;

    @Override
    public Order confirmOrder(ShoppingCart shoppingCart) {
        Order order = new Order();
        order.setCustomer(shoppingCart.getCustomer());
        order.setOrderedItems(new HashMap<>(shoppingCart.getAddedItems()));
        double price = order.getOrderedItems().entrySet()
                .stream()
                .mapToDouble(e -> e.getValue() * e.getKey().getPrice())
                .sum();
        order.setPrice(price - price * order.getCustomer().getDiscount());
        orderRepository.save(order);
        shoppingCart.getAddedItems().forEach((key, value) -> {
            key.setAmount(key.getAmount() - value);
            productService.update(key);
        });
        shoppingCartService.clear(shoppingCart);
        return order;
    }
}
