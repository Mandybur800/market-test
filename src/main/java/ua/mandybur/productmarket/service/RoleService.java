package ua.mandybur.productmarket.service;

import ua.mandybur.productmarket.model.Role;

public interface RoleService {
    Role add(Role role);

    Role get(String role);
}
