package portfolio.krabs.api.command.expense.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import portfolio.krabs.api.command.expense.UpdateExpenseCommand;
import portfolio.krabs.api.entity.Expense;
import portfolio.krabs.api.helper.CategoryHelper;
import portfolio.krabs.api.helper.ExpenseHelper;
import portfolio.krabs.api.model.form.SaveOrUpdateExpenseForm;
import portfolio.krabs.api.model.request.SaveOrUpdateExpenseRequest;
import portfolio.krabs.api.repository.ExpenseRepository;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class UpdateExpenseCommandImpl implements UpdateExpenseCommand {
  
  private final ExpenseHelper expenseHelper;
  
  private final CategoryHelper categoryHelper;
  
  private final ExpenseRepository expenseRepository;
  
  @Override
  public Mono<Boolean> execute(SaveOrUpdateExpenseRequest request) {
    return Mono.fromSupplier(() -> request)
      .map(req -> updateExpense(req.getId(), req.getSaveOrUpdateExpenseForm()))
      .map(expenseRepository::save)
      .thenReturn(Boolean.TRUE);
  }
  
  private Expense updateExpense(String id, SaveOrUpdateExpenseForm form) {
    Expense expense = expenseHelper.findExpenseById(id);
    
    expense.setDescription(form.getDescription());
    expense.setAmount(form.getAmount());
    expense.setPaymentMethod(form.getPaymentMethod());
    expense.setUpdatedTime(LocalDateTime.now());
    expense.setCategory(categoryHelper.findCategoryById(form.getCategoryId()));
    
    return expense;
  }
}
