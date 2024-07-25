package portfolio.krabs.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import portfolio.krabs.api.command.category.DeleteCategoryCommand;
import portfolio.krabs.api.command.category.GetAllCategoriesCommand;
import portfolio.krabs.api.command.category.GetCategoryByIdCommand;
import portfolio.krabs.api.command.category.SaveCategoryCommand;
import portfolio.krabs.api.command.category.UpdateCategoryCommand;
import portfolio.krabs.api.command.executor.CommandExecutor;
import portfolio.krabs.api.controller.util.ControllerUtil;
import portfolio.krabs.api.model.request.EmptyRequest;
import portfolio.krabs.api.model.request.SaveOrUpdateCategoryRequest;
import portfolio.krabs.api.model.response.CategoryWebResponse;
import portfolio.krabs.api.model.response.Response;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/categories")
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class CategoryController {
  
  private final CommandExecutor commandExecutor;
  
  private final Scheduler scheduler;
  
  @PostMapping
  public Mono<Response<Boolean>> save(@RequestBody SaveOrUpdateCategoryRequest request) {
    return ControllerUtil.doExecute(SaveCategoryCommand.class, request, commandExecutor, scheduler);
  }
  
  @GetMapping
  public Mono<Response<List<CategoryWebResponse>>> get(EmptyRequest request) {
    return ControllerUtil.doExecute(GetAllCategoriesCommand.class, request, commandExecutor, scheduler);
  }
  
  @GetMapping("/{id}")
  public Mono<Response<CategoryWebResponse>> getById(@PathVariable String id) {
    return ControllerUtil.doExecute(GetCategoryByIdCommand.class, id, commandExecutor, scheduler);
  }
  
  @PutMapping("/{id}")
  public Mono<Response<Boolean>> update(@PathVariable String id, @RequestBody SaveOrUpdateCategoryRequest request) {
    return ControllerUtil.doExecute(UpdateCategoryCommand.class,
      SaveOrUpdateCategoryRequest.builder()
        .id(id)
        .icon(request.getIcon())
        .name(request.getName())
        .build(),
      commandExecutor,
      scheduler);
  }
  
  @DeleteMapping("/{id}")
  public Mono<Response<Boolean>> delete(@PathVariable String id) {
    return ControllerUtil.doExecute(DeleteCategoryCommand.class, id, commandExecutor, scheduler);
  }
}
