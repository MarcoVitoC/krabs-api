package portfolio.krabs.api.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import portfolio.krabs.api.model.form.SaveOrUpdateExpenseForm;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveOrUpdateExpenseRequest {
  
  private String id;
  
  private String username;
  
  private SaveOrUpdateExpenseForm saveOrUpdateExpenseForm;
  
}
