package ua.mandybur.productmarket.util;

import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ua.mandybur.productmarket.model.Customer;
import ua.mandybur.productmarket.model.Product;
import ua.mandybur.productmarket.model.Role;
import ua.mandybur.productmarket.security.AuthenticationService;
import ua.mandybur.productmarket.service.CustomerService;
import ua.mandybur.productmarket.service.ProductService;
import ua.mandybur.productmarket.service.RoleService;

@Component
@AllArgsConstructor
public class DataInjector {
    private final CustomerService customerService;
    private final ProductService productService;
    private final RoleService roleService;
    private final AuthenticationService authenticationService;

    @PostConstruct
    private void init() {
        Role adminRole = new Role();
        adminRole.setType(Role.RoleType.ADMIN);
        roleService.add(adminRole);

        Role userRole = new Role();
        userRole.setType(Role.RoleType.USER);
        roleService.add(userRole);

        Customer customer = authenticationService.register("gg@gmail.com", "12345678");
        customer.setRoles(Set.of(adminRole, userRole));
        customer.setDiscount(0.2);
        customer.setName("Bob");
        customerService.update(customer);

        Product apple = new Product();
        apple.setName("apple");
        apple.setAmount(1000);
        apple.setPrice(20.4);
        productService.add(apple);

        Product banana = new Product();
        banana.setName("banana");
        banana.setAmount(1000);
        banana.setPrice(15.5);
        productService.add(banana);
    }
}
