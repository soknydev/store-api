package devkh.asia.store_api.features.product;

import devkh.asia.store_api.domain.Product;
import devkh.asia.store_api.features.product.dto.ProductResponse;
import devkh.asia.store_api.features.product.dto.ProductResponseDetails;
import devkh.asia.store_api.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Page<ProductResponse> findProducts(int page, int size) {
        // validate page and size
        if(page<0){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Page number must be grater than 0");
        }
        if(size<1){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "size number must be grater than or equal 1");
        }
        Sort sortByProductName = Sort.by(Sort.Direction.ASC, "name");
        PageRequest pageRequest = PageRequest.of(page, size, sortByProductName);
        Page<Product> products = productRepository.findAll(pageRequest);
        return products.map(productMapper::toProductResponse);
    }

    @Override
    public ProductResponseDetails findProductByUuid(String uuid) {
        Product product = productRepository.findByUuid(uuid).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product has been not found...!"
                )
        );
        return productMapper.toProductResponseDetails(product);
    }


}
