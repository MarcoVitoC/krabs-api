package portfolio.krabs.api.command.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import portfolio.krabs.api.model.constant.Errors;

import java.util.HashMap;
import java.util.Map;

@Getter
public class CommandErrorException extends RuntimeException {
  
  private final HttpStatus httpStatus;
  
  private final Map<String, String> error;
  
  public CommandErrorException(HttpStatus httpStatus, Map<String, String> error) {
    this.httpStatus = httpStatus;
    this.error = error;
  }
  
  public static CommandErrorException withError(HttpStatus httpStatus, Errors error) {
    return new CommandErrorException(httpStatus, Map.of(error.getErrorKey(), error.getErrorMessage()));
  }
}
