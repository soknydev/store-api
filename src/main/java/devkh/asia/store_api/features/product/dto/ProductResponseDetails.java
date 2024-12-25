package devkh.asia.store_api.features.product.dto;

import devkh.asia.store_api.domain.User;

import java.util.List;

public record ProductResponseDetails(
        String name,
        String uuid,
        Integer quantity,
        Double price,
        Boolean isDeleted,
        List<User> users
) {
}
