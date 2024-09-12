package portfolio.krabs.api.command.category.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import portfolio.krabs.api.command.category.UpdateCategoryCommand;
import portfolio.krabs.api.entity.Category;
import portfolio.krabs.api.helper.CategoryHelper;
import portfolio.krabs.api.model.request.SaveOrUpdateCategoryRequest;
import portfolio.krabs.api.repository.CategoryRepository;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class UpdateCategoryCommandImpl implements UpdateCategoryCommand {
  
  private CategoryHelper categoryHelper;
  
  private CategoryRepository categoryRepository;
  
  @Override
  public Mono<Boolean> execute(SaveOrUpdateCategoryRequest request) {
    return Mono.fromSupplier(() -> request)
      .map(this::updateCategory)
      .map(categoryRepository::save)
      .thenReturn(Boolean.TRUE);
  }
  
  private Category updateCategory(SaveOrUpdateCategoryRequest request) {
    Category category = categoryHelper.findCategoryById(request.getId());
    category.setIcon(request.getIcon());
    category.setName(request.getName());
    category.setUpdatedTime(LocalDateTime.now());
    
    return category;
  }
}
