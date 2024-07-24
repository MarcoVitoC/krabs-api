package portfolio.krabs.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import portfolio.krabs.api.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, String> {
}
