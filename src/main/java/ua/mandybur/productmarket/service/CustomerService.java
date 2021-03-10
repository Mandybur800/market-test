package ua.mandybur.productmarket.service;

import ua.mandybur.productmarket.model.Customer;

public interface CustomerService {
    Customer add(Customer customer);

    Customer update(Customer customer);

    Customer findByEmail(String email);
}
