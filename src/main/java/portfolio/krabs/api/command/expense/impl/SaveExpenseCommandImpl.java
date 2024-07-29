package portfolio.krabs.api.command.expense.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import portfolio.krabs.api.command.expense.SaveExpenseCommand;
import portfolio.krabs.api.entity.Expense;
import portfolio.krabs.api.helper.CategoryHelper;
import portfolio.krabs.api.model.request.SaveOrUpdateExpenseRequest;
import portfolio.krabs.api.repository.ExpenseRepository;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class SaveExpenseCommandImpl implements SaveExpenseCommand {
  
  @Autowired
  private CategoryHelper categoryHelper;
  
  @Autowired
  private ExpenseRepository expenseRepository;
  
  @Override
  public Mono<Boolean> execute(SaveOrUpdateExpenseRequest request) {
    return Mono.fromSupplier(() -> request)
      .map(this::parseToEntity)
      .map(expenseRepository::save)
      .thenReturn(Boolean.TRUE);
  }
  
  private Expense parseToEntity(SaveOrUpdateExpenseRequest request) {
    return Expense.builder()
      .description(request.getDescription())
      .amount(request.getAmount())
      .paymentMethod(request.getPaymentMethod())
      .createdTime(LocalDateTime.now())
      .category(categoryHelper.findCategoryById(request.getCategoryId()))
      .user(null)
      .build();
  }
  
}
