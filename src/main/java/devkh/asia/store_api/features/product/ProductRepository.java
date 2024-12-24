package devkh.asia.store_api.features.product;

import devkh.asia.store_api.domain.Product;
import devkh.asia.store_api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByUuid(String uuid);


}
