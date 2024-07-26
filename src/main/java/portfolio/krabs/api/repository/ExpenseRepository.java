package portfolio.krabs.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import portfolio.krabs.api.entity.Expense;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, String> {
  
  @Query("SELECT E FROM Expense E"
    + " WHERE MONTH(E.createdTime) = :month AND YEAR(E.createdTime) = :year")
  List<Expense> findAllByMonthAndYear(int month, int year);
  
}
