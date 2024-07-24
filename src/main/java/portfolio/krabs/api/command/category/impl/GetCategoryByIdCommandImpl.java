package portfolio.krabs.api.command.category.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import portfolio.krabs.api.command.category.GetCategoryByIdCommand;
import portfolio.krabs.api.entity.Category;
import portfolio.krabs.api.model.response.CategoryWebResponse;
import portfolio.krabs.api.repository.CategoryRepository;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GetCategoryByIdCommandImpl implements GetCategoryByIdCommand {
  
  @Autowired
  private CategoryRepository categoryRepository;
  
  @Override
  public Mono<CategoryWebResponse> execute(String id) {
    return null;
  }
  
  private CategoryWebResponse parseToWebResponse(Category category) {
    return CategoryWebResponse.builder()
      .build();
  }
}
