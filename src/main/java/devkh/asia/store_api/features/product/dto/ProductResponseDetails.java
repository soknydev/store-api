package devkh.asia.store_api.features.product.dto;

public record ProductResponseDetails(
        String name,
        String uuid,
        Integer quantity,
        Double price,
        Boolean isDeleted
) {
}
