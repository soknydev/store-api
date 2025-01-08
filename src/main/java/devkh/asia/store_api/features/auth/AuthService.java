package devkh.asia.store_api.features.auth;

import devkh.asia.store_api.features.auth.dto.AuthResponse;
import devkh.asia.store_api.features.auth.dto.LoginRequest;
import devkh.asia.store_api.features.auth.dto.RefreshTokenRequest;

public interface AuthService {

    AuthResponse refresh(RefreshTokenRequest refreshTokenRequest);

    AuthResponse login(LoginRequest loginRequest);

}
