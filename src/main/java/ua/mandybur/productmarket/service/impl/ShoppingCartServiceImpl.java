package ua.mandybur.productmarket.service.impl;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.mandybur.productmarket.model.Customer;
import ua.mandybur.productmarket.model.Product;
import ua.mandybur.productmarket.model.ShoppingCart;
import ua.mandybur.productmarket.repository.ShoppingCartRepository;
import ua.mandybur.productmarket.service.ShoppingCartService;

@Service
@AllArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    public ShoppingCart registerShoppingCart(Customer customer) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCustomer(customer);
        shoppingCart.setAddedItems(new HashMap<>());
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart getByCustomer(Customer customer) {
        return shoppingCartRepository.getByCustomer(customer).orElseThrow(() ->
                new EntityNotFoundException("Can't get cart by customers id: "
                        + customer.getId()));
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.getAddedItems().clear();
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void addProduct(ShoppingCart shoppingCart, Product product, int amount) {
        if (amount > product.getAmount()) {
            throw new RuntimeException("Not enough products! Available " + product.getAmount()
                    + " " + product.getName());
        }
        Map<Product, Integer> products = shoppingCart.getAddedItems();
        if (products.containsKey(product)) {
            products.put(product, products.get(product) + amount);
        } else {
            shoppingCart.getAddedItems().put(product, amount);
        }
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void removeProduct(ShoppingCart shoppingCart, Product product) {
        shoppingCart.getAddedItems().remove(product);
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart get(Long id) {
        return shoppingCartRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Can't get cart by id: " + id));
    }
}
