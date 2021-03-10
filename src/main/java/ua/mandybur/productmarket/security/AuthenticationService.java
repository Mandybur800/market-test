package ua.mandybur.productmarket.security;

import ua.mandybur.productmarket.model.Customer;

public interface AuthenticationService {
    Customer register(String email, String password);
}
