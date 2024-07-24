package portfolio.krabs.api.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
  
  private Integer code;
  
  private String status;
  
  private T data;
  
  private Map<String, String> error;
  
  public static <T> Response<T> ok(T data) {
    return Response.<T>builder()
      .code(HttpStatus.OK.value())
      .status(HttpStatus.OK.name())
      .data(data)
      .build();
  }
}
