package portfolio.krabs.api.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import portfolio.krabs.api.entity.Expense;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryWebResponse {
  
  private String id;
  
  private String icon;
  
  private String name;
  
  private Date createdTime;
  
  private Date updatedTime;
  
  private List<Expense> expenses;
  
}
