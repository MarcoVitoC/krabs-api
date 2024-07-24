package portfolio.krabs.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CATEGORIES")
public class Category {

  @Id
  @UuidGenerator
  @Column(name = "ID", length = 36)
  private String id;
  
  @Column(name = "ICON", length = 10)
  private String icon;
  
  @Column(name = "NAME", length = 50)
  private String name;
  
  @Column(name = "CREATED_TIME")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdTime;
  
  @Column(name = "UPDATED_TIME")
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedTime;
  
  @OneToMany(mappedBy = "category")
  private List<Expense> expenses;
  
}
