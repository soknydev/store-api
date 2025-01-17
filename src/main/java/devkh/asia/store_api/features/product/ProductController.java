package devkh.asia.store_api.features.product;

import devkh.asia.store_api.features.product.dto.ProductCreateRequest;
import devkh.asia.store_api.features.product.dto.ProductResponse;
import devkh.asia.store_api.features.product.dto.ProductResponseDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @RequestMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createNewProduct(@RequestBody @Valid ProductCreateRequest createRequest){
        productService.createNewProduct(createRequest);
    }

    @GetMapping("/{uuid}")
    ProductResponseDetails findProductByUuid(@PathVariable String uuid){
        return productService.findProductByUuid(uuid);
    }

    @GetMapping
    Page<ProductResponse> findProduct(
        @RequestParam(required = false, defaultValue = "0") int page,
        @RequestParam(required = false, defaultValue = "10") int size
    ){
        return productService.findProducts(page,size);
    }

}
