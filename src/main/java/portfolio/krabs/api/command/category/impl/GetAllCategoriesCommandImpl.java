package portfolio.krabs.api.command.category.impl;

import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import portfolio.krabs.api.command.category.GetAllCategoriesCommand;
import portfolio.krabs.api.helper.CategoryHelper;
import portfolio.krabs.api.model.request.EmptyRequest;
import portfolio.krabs.api.model.response.CategoryWebResponse;
import portfolio.krabs.api.repository.CategoryRepository;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class GetAllCategoriesCommandImpl implements GetAllCategoriesCommand {
  
  @Autowired
  private CategoryRepository categoryRepository;
  
  @Autowired
  private CategoryHelper categoryHelper;
  
  @Override
  public Mono<List<CategoryWebResponse>> execute(EmptyRequest request) {
    return Mono.just(categoryRepository.findAll()
      .stream()
      .peek(category -> Optional.ofNullable(category.getExpenses()).ifPresent(Hibernate::initialize))
      .map(categoryHelper::parseToWebResponse)
      .toList()
    );
  }
}
