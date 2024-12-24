package devkh.asia.store_api.features.product;

import devkh.asia.store_api.features.product.dto.ProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService {

    Page<ProductResponse> findProducts(int page, int size);

}
