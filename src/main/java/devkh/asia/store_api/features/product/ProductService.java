package devkh.asia.store_api.features.product;

import devkh.asia.store_api.features.product.dto.ProductResponse;
import devkh.asia.store_api.features.product.dto.ProductResponseDetails;
import org.springframework.data.domain.Page;

public interface ProductService {

    Page<ProductResponse> findProducts(int page, int size);

    ProductResponseDetails findProductByUuid(String uuid);

}
