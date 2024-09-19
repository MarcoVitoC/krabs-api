package portfolio.krabs.api.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import portfolio.krabs.api.command.exception.CommandErrorException;
import portfolio.krabs.api.model.constant.Errors;
import portfolio.krabs.api.model.response.Response;

import java.util.Map;

@RestControllerAdvice
public class ExceptionController {

  @ExceptionHandler(CommandErrorException.class)
  public Response<Object> commandErrorException(CommandErrorException exception) {
    return Response.builder()
      .code(exception.getHttpStatus().value())
      .status(exception.getHttpStatus().name())
      .error(exception.getError())
      .build();
  }
  
  @ExceptionHandler(BadCredentialsException.class)
  public Response<Object> badCredentialsException(BadCredentialsException exception) {
    return Response.builder()
      .code(HttpStatus.BAD_REQUEST.value())
      .status(HttpStatus.BAD_REQUEST.name())
      .error(Map.of(Errors.INVALID_CREDENTIALS.getErrorKey(), Errors.INVALID_CREDENTIALS.getErrorMessage()))
      .build();
  }
  
}
