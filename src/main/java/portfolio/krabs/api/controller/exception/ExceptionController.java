package portfolio.krabs.api.controller.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import portfolio.krabs.api.command.exception.CommandErrorException;
import portfolio.krabs.api.model.response.Response;

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
}
