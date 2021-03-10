package ua.mandybur.productmarket.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.mandybur.productmarket.model.Order;
import ua.mandybur.productmarket.model.ShoppingCart;
import ua.mandybur.productmarket.model.dto.OrderResponseDto;
import ua.mandybur.productmarket.service.OrderService;
import ua.mandybur.productmarket.service.ShoppingCartService;
import ua.mandybur.productmarket.service.mapper.OrderMapper;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ShoppingCartService shoppingCartService;
    private final OrderMapper mapper;

    @GetMapping("/complete/{id}")
    OrderResponseDto completeOrder(@PathVariable Long id) {
        ShoppingCart shoppingCart = shoppingCartService
                .get(id);
        Order order = orderService.confirmOrder(shoppingCart);
        return mapper.getDto(order);
    }
}
