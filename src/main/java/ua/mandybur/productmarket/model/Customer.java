package ua.mandybur.productmarket.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@Entity(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    @Min(8)
    @Column(nullable = false)
    private String password;
    @Min(0)
    @Max(1)
    private double discount;
    @ManyToMany
    private Set<Role> roles;
}
