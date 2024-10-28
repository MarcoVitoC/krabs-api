package portfolio.krabs.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import portfolio.krabs.api.command.executor.CommandExecutor;
import portfolio.krabs.api.command.expense.DeleteExpenseCommand;
import portfolio.krabs.api.command.expense.GetExpenseByIdCommand;
import portfolio.krabs.api.command.expense.SaveExpenseCommand;
import portfolio.krabs.api.command.expense.UpdateExpenseCommand;
import portfolio.krabs.api.command.expense.impl.GetAllExpensesCommandImpl;
import portfolio.krabs.api.controller.util.ControllerUtil;
import portfolio.krabs.api.model.form.SaveOrUpdateExpenseForm;
import portfolio.krabs.api.model.request.GetAllExpensesRequest;
import portfolio.krabs.api.model.request.SaveOrUpdateExpenseRequest;
import portfolio.krabs.api.model.response.ExpenseWebResponse;
import portfolio.krabs.api.model.response.Response;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/expenses")
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class ExpenseController {

  private final CommandExecutor commandExecutor;
  
  private final Scheduler scheduler;
  
  @PostMapping
  public Mono<Response<Boolean>> save(@RequestHeader String username, @RequestBody SaveOrUpdateExpenseForm form) {
    return ControllerUtil.doExecute(SaveExpenseCommand.class,
      SaveOrUpdateExpenseRequest.builder()
        .username(username)
        .saveOrUpdateExpenseForm(form)
        .build(),
      commandExecutor,
      scheduler
    );
  }
  
  @GetMapping
  public Mono<Response<Map<LocalDate, List<ExpenseWebResponse>>>> get(@RequestParam int month, @RequestParam int year) {
    return ControllerUtil.doExecute(GetAllExpensesCommandImpl.class,
      GetAllExpensesRequest.builder()
        .month(month)
        .year(year)
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
  public Mono<Response<Boolean>> update(@PathVariable String id, @RequestBody SaveOrUpdateExpenseForm form) {
    return ControllerUtil.doExecute(UpdateExpenseCommand.class,
      SaveOrUpdateExpenseRequest.builder()
        .id(id)
        .saveOrUpdateExpenseForm(form)
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
