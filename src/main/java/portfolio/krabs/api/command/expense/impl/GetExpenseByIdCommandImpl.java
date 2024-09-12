package portfolio.krabs.api.command.expense.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import portfolio.krabs.api.command.expense.GetExpenseByIdCommand;
import portfolio.krabs.api.helper.ExpenseHelper;
import portfolio.krabs.api.model.response.ExpenseWebResponse;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class GetExpenseByIdCommandImpl implements GetExpenseByIdCommand {
  
  private final ExpenseHelper expenseHelper;
  
  @Override
  public Mono<ExpenseWebResponse> execute(String id) {
    return Mono.fromSupplier(() -> id)
      .map(expenseHelper::findExpenseById)
      .map(expenseHelper::parseToWebResponse);
  }
  
}
