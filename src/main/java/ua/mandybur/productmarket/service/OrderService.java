package ua.mandybur.productmarket.service;

import ua.mandybur.productmarket.model.Order;
import ua.mandybur.productmarket.model.ShoppingCart;

public interface OrderService {
    Order confirmOrder(ShoppingCart shoppingCart);
}
