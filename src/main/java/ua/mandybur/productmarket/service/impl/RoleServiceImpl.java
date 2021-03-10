package ua.mandybur.productmarket.service.impl;

import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.mandybur.productmarket.model.Role;
import ua.mandybur.productmarket.repository.RoleRepository;
import ua.mandybur.productmarket.service.RoleService;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role add(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role get(String role) {
        return roleRepository.findByType(Role.RoleType.valueOf(role.toUpperCase())).orElseThrow(
                () -> new EntityNotFoundException("Can't get role: " + role));
    }
}
