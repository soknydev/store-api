package devkh.asia.store_api.features.product.dto;

import jakarta.validation.constraints.*;

public record ProductCreateRequest(

        @NotNull(message = "Product name is required")
        String name,

        @NotNull(message = "Quantity is required")
        @Min(value = 1, message = "Quantity must be at least 1")
        @Max(value = 10000, message = "Quantity cannot exceed 10,000")
        Integer quantity,

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.01", message = "Price must be greater than 0")
        @Digits(integer = 10, fraction = 2, message = "Price must have up to 10 digits and 2 decimal places")
        Double price

) {
}
