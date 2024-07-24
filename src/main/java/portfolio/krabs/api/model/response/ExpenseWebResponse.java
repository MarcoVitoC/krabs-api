package portfolio.krabs.api.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseWebResponse {
  
  private String id;
  
  private String userId;
  
  private String categoryId;
  
  private String description;
  
  private Long amount;
  
  private String paymentMethod;
  
  private Date createdTime;
  
  private Date updatedTime;
}
