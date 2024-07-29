package portfolio.krabs.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "EXPENSES")
public class Expense {
  
  @Id
  @UuidGenerator
  @Column(name = "ID", length = 36)
  private String id;
  
  @Column(name = "DESCRIPTION")
  private String description;
  
  @Column(name = "AMOUNT")
  private Long amount;
  
  @Column(name = "PAYMENT_METHOD", length = 50)
  private String paymentMethod;
  
  @Column(name = "CREATED_TIME")
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime createdTime;
  
  @Column(name = "UPDATED_TIME")
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime updatedTime;
  
  @ManyToOne
  @JoinColumn(name = "CATEGORY_ID")
  private Category category;
  
  @ManyToOne
  @JoinColumn(name = "USER_ID")
  private User user;
  
}
