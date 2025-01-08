package devkh.asia.store_api.features.token;

import devkh.asia.store_api.domain.Role;
import devkh.asia.store_api.features.auth.dto.AuthResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    static private final String TOKEN_TYPE = "Bearer";
    private final JwtEncoder jwtEncoder;
    private JwtEncoder refreshJwtEncoder;

    @Qualifier("refreshJwtEncoder")
    @Autowired
    public void setRefreshJwtEncoder(JwtEncoder refreshJwtEncoder) {
        this.refreshJwtEncoder = refreshJwtEncoder;
    }


    @Override
    public AuthResponse createToken(Authentication auth) {
        return new AuthResponse(
                TOKEN_TYPE,
                createAccessToken(auth),
                createRefreshToken(auth)
        );
    }

    @Override
    public String createAccessToken(Authentication auth) {
        String scope = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(authority -> authority.startsWith("SCOPE_") ? authority.substring(6) : authority)
                .collect(Collectors.joining(" ")); // Use space as delimiter

        Instant now = Instant.now();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .id(auth.getName())
                .subject("Access Resource")
                .audience(List.of("Web", "Mobile"))
                .issuedAt(now)
                .expiresAt(now.plus(5, ChronoUnit.MINUTES))
                .issuer(auth.getName())
                .claim("scope", scope)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
    }

    @Override
    public String createRefreshToken(Authentication auth) {
        String scope = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(authority -> authority.startsWith("SCOPE_") ? authority.substring(6) : authority)
                .collect(Collectors.joining(" "));

        Instant now = Instant.now();
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .id(auth.getName())
                .subject("Refresh Resource")
                .audience(List.of("Web", "Mobile"))
                .issuedAt(now)
                .expiresAt(now.plus(5, ChronoUnit.MINUTES))
                .issuer(auth.getName())
                .claim("scope", scope)
                .build();

        return refreshJwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
    }

}
