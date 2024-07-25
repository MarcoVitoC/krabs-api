package portfolio.krabs.api.command.expense.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import portfolio.krabs.api.command.expense.GetAllExpensesCommand;
import portfolio.krabs.api.helper.ExpenseHelper;
import portfolio.krabs.api.model.request.GetAllExpensesRequest;
import portfolio.krabs.api.model.response.ExpenseWebResponse;
import portfolio.krabs.api.repository.ExpenseRepository;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@AllArgsConstructor
public class GetAllExpensesCommandImpl implements GetAllExpensesCommand {
  
  @Autowired
  private ExpenseRepository expenseRepository;
  
  @Autowired
  private ExpenseHelper expenseHelper;
  
  @Override
  public Mono<List<ExpenseWebResponse>> execute(GetAllExpensesRequest request) {
    return Mono.just(expenseRepository.findAllByMonth(request.getMonth())
      .stream()
      .map(expenseHelper::parseToWebResponse)
      .toList()
    );
  }
  
}
