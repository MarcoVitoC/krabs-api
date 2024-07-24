package portfolio.krabs.api.command.category.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import portfolio.krabs.api.command.category.DeleteCategoryCommand;
import portfolio.krabs.api.helper.CategoryHelper;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class DeleteCategoryCommandImpl implements DeleteCategoryCommand {
  
  @Autowired
  private CategoryHelper categoryHelper;
  
  @Override
  public Mono<Boolean> execute(String id) {
    return Mono.fromSupplier(() -> id)
      .map(categoryHelper::findCategoryById)
      .map(categoryHelper::deleteCategory);
  }
}
