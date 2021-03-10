package ua.mandybur.productmarket.security;

import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.mandybur.productmarket.model.Customer;
import ua.mandybur.productmarket.service.CustomerService;
import ua.mandybur.productmarket.service.RoleService;
import ua.mandybur.productmarket.service.ShoppingCartService;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final CustomerService customerService;
    private final RoleService roleService;
    private final ShoppingCartService shoppingCartService;

    @Override
    public Customer register(String email, String password) {
        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setRoles(Set.of(roleService.get("USER")));
        customerService.add(customer);
        shoppingCartService.registerShoppingCart(customer);
        return customer;
    }
}
