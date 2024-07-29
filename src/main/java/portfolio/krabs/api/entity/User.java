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

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class User {
  
  @Id
  @UuidGenerator
  @Column(name = "ID", length = 36)
  private String id;
  
  @Column(name = "USERNAME", length = 50)
  private String username;
  
  @Column(name = "EMAIL", length = 50)
  private String email;
  
  @Column(name = "PASSWORD", length = 50)
  private String password;
  
  @Column(name = "CREATED_TIME")
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime createdTime;
  
  @Column(name = "UPDATED_TIME")
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime updatedTime;
  
  @OneToMany(mappedBy = "user")
  private List<Expense> expenses;
  
}
