package ua.mandybur.productmarket.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.mandybur.productmarket.model.Product;
import ua.mandybur.productmarket.model.ShoppingCart;
import ua.mandybur.productmarket.service.ProductService;
import ua.mandybur.productmarket.service.ShoppingCartService;

@RestController
@AllArgsConstructor
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;

    @GetMapping("/{cartId}")
    public void addProductToCart(@PathVariable Long cartId,
                           @RequestParam Long productId,
                           @RequestParam int amount) {
        ShoppingCart shoppingCart = shoppingCartService.get(cartId);
        Product product = productService.get(productId);
        shoppingCartService.addProduct(shoppingCart, product, amount);
    }

    @DeleteMapping("/{cartId}")
    public void removeProductFromCart(@PathVariable Long cartId,
                           @RequestParam Long productId) {
        ShoppingCart shoppingCart = shoppingCartService.get(cartId);
        Product product = productService.get(productId);
        shoppingCartService.removeProduct(shoppingCart, product);
    }
}
