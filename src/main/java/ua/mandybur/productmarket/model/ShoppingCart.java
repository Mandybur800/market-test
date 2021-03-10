package ua.mandybur.productmarket.model;

import java.util.Map;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.ToString;

@Data
@Entity(name = "shopping_carts")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(nullable = false)
    @ToString.Exclude
    private Customer customer;
    @ElementCollection
    @CollectionTable(name = "products_in_cart_mapping",
            joinColumns = {@JoinColumn(name = "cart_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "product_id")
    @Column(name = "amount")
    private Map<Product, Integer> addedItems;
}
