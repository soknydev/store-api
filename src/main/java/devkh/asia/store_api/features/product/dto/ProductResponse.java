package devkh.asia.store_api.features.product.dto;

public record ProductResponse(
        String name,
        String uuid,
        Integer quantity,
        Double price,
        Boolean isDeleted
) {
}
