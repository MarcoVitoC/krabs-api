package portfolio.krabs.api.command.expense.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import portfolio.krabs.api.command.expense.GetAllExpensesCommand;
import portfolio.krabs.api.entity.Expense;
import portfolio.krabs.api.helper.ExpenseHelper;
import portfolio.krabs.api.model.request.GetAllExpensesRequest;
import portfolio.krabs.api.model.response.ExpenseWebResponse;
import portfolio.krabs.api.repository.ExpenseRepository;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class GetAllExpensesCommandImpl implements GetAllExpensesCommand {
  
  @Autowired
  private ExpenseRepository expenseRepository;
  
  @Autowired
  private ExpenseHelper expenseHelper;
  
  @Override
  public Mono<Map<LocalDate, List<ExpenseWebResponse>>> execute(GetAllExpensesRequest request) {
    List<Expense> expenses = expenseRepository.findAllByMonthAndYear(request.getMonth(), request.getYear());
    
    LocalDate firstDay = LocalDate.of(request.getYear(), request.getMonth(), 1);
    LocalDate lastDay = YearMonth.of(request.getYear(), request.getMonth()).atEndOfMonth();
    
    Map<LocalDate, List<ExpenseWebResponse>> monthlyExpenses = new HashMap<>();
    for (LocalDate date = firstDay; date.isBefore(lastDay); date = date.plusDays(1)) {
      LocalDate currentDate = date;
      List<ExpenseWebResponse> dailyExpenses = expenses.stream()
        .filter(expense -> expense.getCreatedTime().toLocalDate().isEqual(currentDate))
        .map(expenseHelper::parseToWebResponse)
        .toList();
      
      monthlyExpenses.put(date, dailyExpenses);
    }
    
    return Mono.just(monthlyExpenses);
  }
  
}
