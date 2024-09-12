package portfolio.krabs.api.command.category.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import portfolio.krabs.api.command.category.SaveCategoryCommand;
import portfolio.krabs.api.entity.Category;
import portfolio.krabs.api.model.request.SaveOrUpdateCategoryRequest;
import portfolio.krabs.api.repository.CategoryRepository;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class SaveCategoryCommandImpl implements SaveCategoryCommand {
  
  private CategoryRepository categoryRepository;
  
  @Override
  public Mono<Boolean> execute(SaveOrUpdateCategoryRequest request) {
    return Mono.fromSupplier(() -> request)
      .map(this::parseToEntity)
      .map(categoryRepository::save)
      .thenReturn(Boolean.TRUE);
  }
  
  private Category parseToEntity(SaveOrUpdateCategoryRequest request) {
    return Category.builder()
      .icon(request.getIcon())
      .name(request.getName())
      .createdTime(LocalDateTime.now())
      .build();
  }
}
