package portfolio.krabs.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import portfolio.krabs.api.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
  
  User findByUsername(String username);
  
}
