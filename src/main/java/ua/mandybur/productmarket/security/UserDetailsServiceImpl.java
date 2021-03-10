package ua.mandybur.productmarket.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.mandybur.productmarket.model.Customer;
import ua.mandybur.productmarket.service.CustomerService;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final CustomerService customerService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerService.findByEmail(email);
        UserBuilder builder = User.builder();
        builder.username(customer.getEmail());
        builder.password(customer.getPassword());
        builder.authorities(customer.getRoles()
                .stream()
                .map(n -> n.getType().name())
                .toArray(String[]::new));
        return builder.build();
    }
}
