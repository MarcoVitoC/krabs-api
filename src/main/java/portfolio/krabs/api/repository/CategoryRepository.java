package portfolio.krabs.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import portfolio.krabs.api.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
