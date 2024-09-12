package portfolio.krabs.api.command.category.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import portfolio.krabs.api.command.category.GetCategoryByIdCommand;
import portfolio.krabs.api.helper.CategoryHelper;
import portfolio.krabs.api.model.response.CategoryWebResponse;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class GetCategoryByIdCommandImpl implements GetCategoryByIdCommand {
  
  private CategoryHelper categoryHelper;
  
  @Override
  public Mono<CategoryWebResponse> execute(String id) {
    return Mono.fromSupplier(() -> id)
      .map(categoryHelper::findCategoryById)
      .map(categoryHelper::parseToWebResponse);
  }
}
