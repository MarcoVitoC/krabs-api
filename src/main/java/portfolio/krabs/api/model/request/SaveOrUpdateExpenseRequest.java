package portfolio.krabs.api.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveOrUpdateExpenseRequest {
  
  private String id;
  
  private String categoryId;
  
  private String description;
  
  private Long amount;
  
  private String paymentMethod;
  
}
