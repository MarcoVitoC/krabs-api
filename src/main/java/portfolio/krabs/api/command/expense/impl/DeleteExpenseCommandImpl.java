package portfolio.krabs.api.command.expense.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import portfolio.krabs.api.command.expense.DeleteExpenseCommand;
import portfolio.krabs.api.repository.ExpenseRepository;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class DeleteExpenseCommandImpl implements DeleteExpenseCommand {
  
  @Autowired
  private ExpenseRepository expenseRepository;
  
  @Override
  public Mono<Boolean> execute(String id) {
    return Mono.fromRunnable(() -> expenseRepository.deleteById(id))
      .thenReturn(Boolean.TRUE);
  }
}
