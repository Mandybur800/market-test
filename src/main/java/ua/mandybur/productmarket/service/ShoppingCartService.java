package ua.mandybur.productmarket.service;

import ua.mandybur.productmarket.model.Customer;
import ua.mandybur.productmarket.model.Product;
import ua.mandybur.productmarket.model.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCart registerShoppingCart(Customer customer);

    ShoppingCart getByCustomer(Customer customer);

    void clear(ShoppingCart shoppingCart);

    void addProduct(ShoppingCart shoppingCart, Product product, int amount);

    void removeProduct(ShoppingCart shoppingCart, Product product);

    ShoppingCart get(Long id);
}
