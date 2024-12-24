package devkh.asia.store_api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String name;

    @Column(unique = true, nullable = false, length = 50)
    private String uuid;

    @ManyToMany
    @JoinTable(
            name = "users_products", // Join table
            joinColumns = @JoinColumn(name = "product_id"), // Foreign key in the join table for Product
            inverseJoinColumns = @JoinColumn(name = "user_id") // Foreign key in the join table for User
    )
    private List<User> users;

    private Integer quantity;
    private Double price;
    private Boolean isDeleted = false;

}
