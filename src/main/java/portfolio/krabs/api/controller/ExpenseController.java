package portfolio.krabs.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import portfolio.krabs.api.command.executor.CommandExecutor;
import portfolio.krabs.api.command.expense.DeleteExpenseCommand;
import portfolio.krabs.api.command.expense.GetExpenseByIdCommand;
import portfolio.krabs.api.command.expense.SaveExpenseCommand;
import portfolio.krabs.api.command.expense.UpdateExpenseCommand;
import portfolio.krabs.api.command.expense.impl.GetAllExpensesCommandImpl;
import portfolio.krabs.api.controller.util.ControllerUtil;
import portfolio.krabs.api.model.request.GetAllExpensesRequest;
import portfolio.krabs.api.model.request.SaveOrUpdateExpenseRequest;
import portfolio.krabs.api.model.response.ExpenseWebResponse;
import portfolio.krabs.api.model.response.Response;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/expenses")
public class ExpenseController {

  private final CommandExecutor commandExecutor;
  
  private final Scheduler scheduler;
  
  @PostMapping
  public Mono<Response<Boolean>> save(@RequestBody SaveOrUpdateExpenseRequest request) {
    return ControllerUtil.doExecute(SaveExpenseCommand.class, request, commandExecutor, scheduler);
  }
  
  @GetMapping
  public Mono<Response<List<ExpenseWebResponse>>> get(@RequestParam int month) {
    return ControllerUtil.doExecute(GetAllExpensesCommandImpl.class,
      GetAllExpensesRequest.builder()
        .month(month)
        .build(),
      commandExecutor,
      scheduler
    );
  }
  
  @GetMapping("/{id}")
  public Mono<Response<ExpenseWebResponse>> getById(@PathVariable String id) {
    return ControllerUtil.doExecute(GetExpenseByIdCommand.class, id, commandExecutor, scheduler);
  }
  
  @PutMapping("/{id}")
  public Mono<Response<Boolean>> update(@PathVariable String id, @RequestBody SaveOrUpdateExpenseRequest request) {
    return ControllerUtil.doExecute(UpdateExpenseCommand.class,
      SaveOrUpdateExpenseRequest.builder()
        .id(id)
        .description(request.getDescription())
        .amount(request.getAmount())
        .paymentMethod(request.getPaymentMethod())
        .categoryId(request.getCategoryId())
        .build(),
      commandExecutor,
      scheduler
    );
  }
  
  @DeleteMapping("/{id}")
  public Mono<Response<Boolean>> delete(@PathVariable String id) {
    return ControllerUtil.doExecute(DeleteExpenseCommand.class, id, commandExecutor, scheduler);
  }
}
