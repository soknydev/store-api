package devkh.asia.store_api.features.auth.dto;

public record AuthResponse(
        String type,
        String accessToken,
        String refreshToken
) {
}
