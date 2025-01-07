package devkh.asia.store_api.features.auth;

import devkh.asia.store_api.domain.Role;
import devkh.asia.store_api.features.auth.dto.AuthResponse;
import devkh.asia.store_api.features.auth.dto.LoginRequest;
import devkh.asia.store_api.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final JwtEncoder jwtEncoder;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {

        Authentication auth = new UsernamePasswordAuthenticationToken(
                loginRequest.username(), loginRequest.password());

        auth = daoAuthenticationProvider.authenticate(auth);
        CustomUserDetails customUserDetails = (CustomUserDetails) auth.getPrincipal();

        log.info("Authenticated user: {}", customUserDetails.getUsername());

        Instant now = Instant.now();

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .id(customUserDetails.getUsername())
                .subject("Access Resource")
                .audience(List.of("Web", "Mobile"))
                .issuedAt(now)
                .expiresAt(now.plus(3, ChronoUnit.MINUTES))
                .issuer("store-api")
                .claim("roles", customUserDetails.getUser().getRoles().stream()
                        .map(Role::getName)
                        .toList())
                .claim("authorities", customUserDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList())
                .build();


        String accessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();

        return new AuthResponse(
                "Bearer",
                accessToken,
                "this is refresh token"
        );
    }
}
