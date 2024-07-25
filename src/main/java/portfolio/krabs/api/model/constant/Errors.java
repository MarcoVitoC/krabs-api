package portfolio.krabs.api.model.constant;

import lombok.Getter;

@Getter
public enum Errors {
  
  CATEGORY_NOT_FOUND("CATEGORY_NOT_FOUND", "Category not found!"),
  EXPENSE_NOT_FOUND("EXPENSE_NOT_FOUND", "Expense not found!");
  
  private final String errorKey;
  
  private final String errorMessage;
  
  Errors(String errorKey, String errorMessage) {
    this.errorKey = errorKey;
    this.errorMessage = errorMessage;
  }
}
