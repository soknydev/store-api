package devkh.asia.store_api.mapper;

import devkh.asia.store_api.domain.Product;
import devkh.asia.store_api.features.product.dto.ProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponse toProductResponse(Product product);

}
