package devkh.asia.store_api.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;

    @Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        UserDetails userAdmin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("USER", "ADMIN")
                .build();

        UserDetails userStaff = User.builder()
                .username("staff")
                .password(passwordEncoder.encode("staff123"))
                .roles("USER", "STAFF")
                .build();

        manager.createUser(userAdmin);
        manager.createUser(userStaff);
        return manager;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // security logic
        httpSecurity.authorizeHttpRequests(
                request -> request.requestMatchers("/api/v1/products/**")
                        .hasRole("ADMIN")
                        .anyRequest()
                        .authenticated());

        httpSecurity.httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }

}
