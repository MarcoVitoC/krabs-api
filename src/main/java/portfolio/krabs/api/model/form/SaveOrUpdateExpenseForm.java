package portfolio.krabs.api.model.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveOrUpdateExpenseForm {
  
  private String categoryId;
  
  private String description;
  
  private Long amount;
  
  private String paymentMethod;
  
}
