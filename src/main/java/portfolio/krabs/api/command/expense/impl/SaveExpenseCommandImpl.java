package portfolio.krabs.api.command.expense.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import portfolio.krabs.api.command.expense.SaveExpenseCommand;
import portfolio.krabs.api.entity.Expense;
import portfolio.krabs.api.entity.User;
import portfolio.krabs.api.helper.CategoryHelper;
import portfolio.krabs.api.model.request.SaveOrUpdateExpenseRequest;
import portfolio.krabs.api.repository.ExpenseRepository;
import portfolio.krabs.api.repository.UserRepository;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class SaveExpenseCommandImpl implements SaveExpenseCommand {
  
  private final CategoryHelper categoryHelper;
  
  private final UserRepository userRepository;
  
  private final ExpenseRepository expenseRepository;
  
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
      .user(getUser(request.getUsername()))
      .build();
  }
  
  private User getUser(String username) {
    return userRepository.findByUsername(username)
      .orElse(null);
  }
  
}
