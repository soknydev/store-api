package devkh.asia.store_api.features.auth;

import devkh.asia.store_api.features.auth.dto.AuthResponse;
import devkh.asia.store_api.features.auth.dto.LoginRequest;
import devkh.asia.store_api.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService{

    private final DaoAuthenticationProvider daoAuthenticationProvider;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {

        Authentication auth = new UsernamePasswordAuthenticationToken(
                loginRequest.username(), loginRequest.password());

        auth = daoAuthenticationProvider.authenticate(auth);
        CustomUserDetails customUserDetails = (CustomUserDetails) auth.getCredentials();
        log.info(customUserDetails.getUsername());
        log.info(customUserDetails.getPassword());
        customUserDetails.getAuthorities().forEach(
                grantedAuthority -> System.out.println(grantedAuthority.getAuthority())
        );


        return null;
    }

}
