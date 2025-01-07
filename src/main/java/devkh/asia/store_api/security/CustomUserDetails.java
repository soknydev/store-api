    package devkh.asia.store_api.security;

    import devkh.asia.store_api.domain.User;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;
    import lombok.ToString;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    import org.springframework.security.core.userdetails.UserDetails;

    import java.util.ArrayList;
    import java.util.Collection;
    import java.util.List;
    import java.util.stream.Collectors;
    import java.util.stream.Stream;

    @Setter
    @Getter
    @NoArgsConstructor
    @ToString
    public class CustomUserDetails implements UserDetails {

        private User user;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            /*return user.getRoles().stream()
                    .flatMap(role -> {
                        // Add role itself
                        Stream<GrantedAuthority> roleStream = Stream.of(new SimpleGrantedAuthority("ROLE_" + role.getName()));
                        // Add associated authorities
                        Stream<GrantedAuthority> authorityStream = role.getAuthorities().stream()
                                .map(authority -> new SimpleGrantedAuthority(authority.getName()));
                        return Stream.concat(roleStream, authorityStream);
                    })
                    .collect(Collectors.toList());*/

            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
                role.getAuthorities().forEach(authority -> {
                    authorities.add(new SimpleGrantedAuthority(authority.getName()));
                });
            });
            return authorities;
        }


        @Override
        public String getPassword() {
            return user.getPassword();
        }

        @Override
        public String getUsername() {
            return user.getUsername();
        }

        @Override
        public boolean isAccountNonExpired() {
            return user.isAccountNonExpired();
        }

        @Override
        public boolean isAccountNonLocked() {
            return user.isAccountNonLocked();
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return user.isCredentialsNonExpired();
        }

        @Override
        public boolean isEnabled() {
            return user.isEnabled();
        }

    }
