package portfolio.krabs.api.helper;

import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfolio.krabs.api.command.exception.CommandErrorException;
import portfolio.krabs.api.entity.Category;
import portfolio.krabs.api.entity.Expense;
import portfolio.krabs.api.model.constant.Errors;
import portfolio.krabs.api.model.response.CategoryWebResponse;
import portfolio.krabs.api.model.response.ExpenseWebResponse;
import portfolio.krabs.api.repository.CategoryRepository;
import portfolio.krabs.api.repository.ExpenseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryHelper {
  
  @Autowired
  private CategoryRepository categoryRepository;
  
  @Autowired
  private ExpenseRepository expenseRepository;
  
  @Transactional(readOnly = true)
  public Category findCategoryById(String id) {
    return categoryRepository.findById(id)
      .map(category -> {
        Optional.ofNullable(category.getExpenses()).ifPresent(Hibernate::initialize);
        return category;
      })
      .orElseThrow(() -> CommandErrorException.withError(HttpStatus.NOT_FOUND, Errors.CATEGORY_NOT_FOUND));
  }
  
  public CategoryWebResponse parseToWebResponse(Category category) {
    CategoryWebResponse categoryWebResponse = CategoryWebResponse.builder().build();
    
    BeanUtils.copyProperties(category, categoryWebResponse, "expenses");
    categoryWebResponse.setExpenses(toExpenseWebResponse(category.getExpenses()));
    
    return categoryWebResponse;
  }
  
  private List<ExpenseWebResponse> toExpenseWebResponse(List<Expense> expenses) {
    return expenses.stream()
      .map(expense -> {
        ExpenseWebResponse expenseWebResponse = ExpenseWebResponse.builder().build();
        
        BeanUtils.copyProperties(expense, expenseWebResponse);
        return expenseWebResponse;
      })
      .toList();
  }
  
  public Boolean deleteCategory(Category category) {
    category.getExpenses().forEach(expense -> expenseRepository.delete(expense));
    categoryRepository.delete(category);
    
    return Boolean.TRUE;
  }
}
