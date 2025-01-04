package devkh.asia.store_api.init;

import devkh.asia.store_api.domain.Authority;
import devkh.asia.store_api.domain.Product;
import devkh.asia.store_api.domain.Role;
import devkh.asia.store_api.domain.User;
import devkh.asia.store_api.features.user.AuthorityRepository;
import devkh.asia.store_api.features.product.ProductRepository;
import devkh.asia.store_api.features.user.RoleRepository;
import devkh.asia.store_api.features.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final AuthorityRepository authorityRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @PostConstruct
    void init() {
        initAuthoritiesAndRoles();
        initProducts();
    }

    private void initAuthoritiesAndRoles() {
        if (authorityRepository.count() == 0) {
            // Initialize authorities
            Authority userRead = new Authority();
            userRead.setName("user:read");

            Authority userWrite = new Authority();
            userWrite.setName("user:write");

            Authority productRead = new Authority();
            productRead.setName("product:read");

            Authority productWrite = new Authority();
            productWrite.setName("product:write");

            authorityRepository.saveAll(List.of(userRead, userWrite, productRead, productWrite));

            if (roleRepository.count() == 0 && userRepository.count() == 0) {
                
                // Create user role:user
                User user = new User();
                user.setUuid("123e4567-e89b-12d3-a456-426614174000");
                user.setName("User");
                user.setUsername("user");
                user.setPassword("user123");
                user.setIsAccountNonExpired(true);
                user.setIsAccountNonLocked(true);
                user.setIsCredentialsNonExpired(true);
                user.setIsDeleted(false);
                user.setIsBlocked(false);
                userRepository.save(user);
                
                // Create user role:admin
                User userAdmin = new User();
                userAdmin.setUuid("123e4567-e89b-12d3-a456-426614172030");
                userAdmin.setName("Admin");
                userAdmin.setUsername("admin");
                userAdmin.setPassword("admin123");
                userAdmin.setIsAccountNonExpired(true);
                userAdmin.setIsAccountNonLocked(true);
                userAdmin.setIsCredentialsNonExpired(true);
                userAdmin.setIsDeleted(false);
                userAdmin.setIsBlocked(false);
                userRepository.save(userAdmin);

                // Create roles and associate authorities
                Role userRole = new Role();
                userRole.setName("USER");
                userRole.setAuthorities(List.of(userRead, productRead));
                userRole.setUser(user);

                Role adminRole = new Role();
                adminRole.setName("ADMIN");
                adminRole.setAuthorities(List.of(userRead, userWrite, productRead, productWrite));
                adminRole.setUser(user);

                // Save roles
                roleRepository.saveAll(List.of(userRole, adminRole));

                // Assign roles to the user
                user.setRoles(List.of(userRole));
                userAdmin.setRoles(List.of(userRole, adminRole));
                userRepository.saveAll(List.of(user, userAdmin));
            }
        }
    }

    private void initProducts() {
        if (productRepository.count() == 0) {
            // Initialize products
            List<Product> products = List.of(
                    createProduct("Laptop", 10, 1200.00),
                    createProduct("Smartphone", 25, 800.00),
                    createProduct("Headphones", 50, 150.00),
                    createProduct("Monitor", 15, 300.00),
                    createProduct("Keyboard", 30, 75.00)
            );
            productRepository.saveAll(products);
        }
    }

    private Product createProduct(String name, int quantity, double price) {
        Product product = new Product();
        product.setName(name);
        product.setUuid(UUID.randomUUID().toString());
        product.setQuantity(quantity);
        product.setPrice(price);
        product.setIsDeleted(false);
        return product;
    }
}
