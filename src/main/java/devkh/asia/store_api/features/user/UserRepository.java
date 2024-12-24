package devkh.asia.store_api.features.user;

import devkh.asia.store_api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
