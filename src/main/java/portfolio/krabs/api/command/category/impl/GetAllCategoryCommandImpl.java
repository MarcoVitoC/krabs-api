package portfolio.krabs.api.command.category.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import portfolio.krabs.api.command.category.GetAllCategoryCommand;
import portfolio.krabs.api.entity.Category;
import portfolio.krabs.api.model.request.EmptyRequest;
import portfolio.krabs.api.model.response.CategoryWebResponse;
import portfolio.krabs.api.repository.CategoryRepository;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllCategoryCommandImpl implements GetAllCategoryCommand {
  
  @Autowired
  private CategoryRepository categoryRepository;
  
  @Override
  public Mono<List<CategoryWebResponse>> execute(EmptyRequest request) {
    List<Category> categories = categoryRepository.findAll();
    
    List<CategoryWebResponse> categoryWebResponses = new ArrayList<>();
    categories.forEach(category -> categoryWebResponses.add(
      CategoryWebResponse.builder()
        .id(category.getId())
        .icon(category.getIcon())
        .name(category.getName())
        .createdTime(category.getCreatedTime())
        .updatedTime(category.getUpdatedTime())
        .expenses(category.getExpenses())
        .build())
    );
    
    return Mono.just(categoryWebResponses);
  }
}
