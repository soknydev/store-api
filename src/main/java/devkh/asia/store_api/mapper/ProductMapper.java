package devkh.asia.store_api.mapper;

import devkh.asia.store_api.domain.Product;
import devkh.asia.store_api.features.product.dto.ProductCreateRequest;
import devkh.asia.store_api.features.product.dto.ProductResponse;
import devkh.asia.store_api.features.product.dto.ProductResponseDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product fromProductCreateRequest(ProductCreateRequest createRequest);

    ProductResponse toProductResponse(Product product);

    ProductResponseDetails toProductResponseDetails(Product product);

}
