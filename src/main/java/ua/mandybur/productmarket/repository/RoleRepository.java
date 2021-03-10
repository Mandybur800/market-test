package ua.mandybur.productmarket.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.mandybur.productmarket.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByType(Role.RoleType type);
}
