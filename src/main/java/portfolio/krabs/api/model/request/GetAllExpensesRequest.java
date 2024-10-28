package portfolio.krabs.api.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllExpensesRequest {
  
  private String username;
  
  private int month;
  
  private int year;
  
}
