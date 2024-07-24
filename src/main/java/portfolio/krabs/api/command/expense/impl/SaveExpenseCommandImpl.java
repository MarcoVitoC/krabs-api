package portfolio.krabs.api.command.expense.impl;

import org.springframework.stereotype.Component;
import portfolio.krabs.api.command.expense.SaveExpenseCommand;
import portfolio.krabs.api.entity.Expense;
import portfolio.krabs.api.model.request.SaveExpenseRequest;
import reactor.core.publisher.Mono;

@Component
public class SaveExpenseCommandImpl implements SaveExpenseCommand {
  
  @Override
  public Mono<Boolean> execute(SaveExpenseRequest request) {
    return null;
  }
  
  private Expense toEntity(SaveExpenseRequest request) {
    return Expense.builder()
      .description(request.getDescription())
      .amount(request.getAmount())
      .paymentMethod(request.getPaymentMethod())
      .build();
  }
  
}
