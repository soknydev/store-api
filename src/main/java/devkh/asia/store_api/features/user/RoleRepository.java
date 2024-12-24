package devkh.asia.store_api.features.user;

import devkh.asia.store_api.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
