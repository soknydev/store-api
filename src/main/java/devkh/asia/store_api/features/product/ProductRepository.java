package devkh.asia.store_api.features.product;

import devkh.asia.store_api.domain.Product;
import devkh.asia.store_api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
