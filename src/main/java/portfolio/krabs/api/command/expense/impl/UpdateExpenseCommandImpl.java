package portfolio.krabs.api.command.expense.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import portfolio.krabs.api.command.expense.UpdateExpenseCommand;
import portfolio.krabs.api.entity.Expense;
import portfolio.krabs.api.helper.CategoryHelper;
import portfolio.krabs.api.helper.ExpenseHelper;
import portfolio.krabs.api.model.request.SaveOrUpdateExpenseRequest;
import portfolio.krabs.api.repository.ExpenseRepository;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Component
@AllArgsConstructor
public class UpdateExpenseCommandImpl implements UpdateExpenseCommand {
  
  @Autowired
  private ExpenseHelper expenseHelper;
  
  @Autowired
  private CategoryHelper categoryHelper;
  
  @Autowired
  private ExpenseRepository expenseRepository;
  
  @Override
  public Mono<Boolean> execute(SaveOrUpdateExpenseRequest request) {
    return Mono.fromSupplier(() -> request)
      .map(this::updateExpense)
      .map(expenseRepository::save)
      .thenReturn(Boolean.TRUE);
  }
  
  private Expense updateExpense(SaveOrUpdateExpenseRequest request) {
    Expense expense = expenseHelper.findExpenseById(request.getId());
    
    expense.setDescription(request.getDescription());
    expense.setAmount(request.getAmount());
    expense.setPaymentMethod(request.getPaymentMethod());
    expense.setUpdatedTime(LocalDate.now());
    expense.setCategory(categoryHelper.findCategoryById(request.getCategoryId()));
    
    return expense;
  }
}
