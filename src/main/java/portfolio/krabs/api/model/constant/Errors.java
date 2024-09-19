package portfolio.krabs.api.model.constant;

import lombok.Getter;

@Getter
public enum Errors {
  
  CATEGORY_NOT_FOUND("CATEGORY_NOT_FOUND", "Category not found!"),
  
  EXPENSE_NOT_FOUND("EXPENSE_NOT_FOUND", "Expense not found!"),
  
  USERNAME_ALREADY_EXIST("USERNAME_ALREADY_EXIST", "Username already exist!"),
  
  INVALID_CREDENTIALS("INVALID_CREDENTIALS", "Please input the correct username or password!"),
  
  UNAUTHORIZED_ACCESS("UNAUTHORIZED", "Please login or register to gain access!"),
  
  FORBIDDEN_ACCESS("FORBIDDEN_ACCESS", "Sorry, You are not allowed to access this resource.");
  
  private final String errorKey;
  
  private final String errorMessage;
  
  Errors(String errorKey, String errorMessage) {
    this.errorKey = errorKey;
    this.errorMessage = errorMessage;
  }
}
