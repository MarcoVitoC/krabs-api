package portfolio.krabs.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import portfolio.krabs.api.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
  
  Optional<User> findByUsername(String username);
  
}
