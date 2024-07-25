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
import portfolio.krabs.api.repository.ExpenseRepository;

import java.util.Optional;

@Service
public class ExpenseHelper {
  
  @Autowired
  private ExpenseRepository expenseRepository;
  
  @Transactional(readOnly = true)
  public Expense findExpenseById(String id) {
    return expenseRepository.findById(id)
      .map(expense -> {
        Optional.ofNullable(expense.getCategory()).ifPresent(Hibernate::initialize);
        Optional.ofNullable(expense.getUser()).ifPresent(Hibernate::initialize);
        
        return expense;
      })
      .orElseThrow(() -> CommandErrorException.withError(HttpStatus.NOT_FOUND, Errors.EXPENSE_NOT_FOUND));
  }
  
  public ExpenseWebResponse parseToWebResponse(Expense expense) {
    ExpenseWebResponse expenseWebResponse = ExpenseWebResponse.builder().build();
    
    BeanUtils.copyProperties(expense, expenseWebResponse);
    expenseWebResponse.setCategory(toCategoryWebResponse(expense.getCategory()));
    
    return expenseWebResponse;
  }
  
  private CategoryWebResponse toCategoryWebResponse(Category category) {
    CategoryWebResponse categoryWebResponse = CategoryWebResponse.builder().build();
    
    BeanUtils.copyProperties(category, categoryWebResponse, "expenses");
    return categoryWebResponse;
  }
  
}
