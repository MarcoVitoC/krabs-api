package portfolio.krabs.api.command.executor;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import portfolio.krabs.api.command.Command;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CommandExecutor {
  
  private final ApplicationContext applicationContext;
  
  public <R, T> Mono<T> execute(Class<? extends Command<R, T>> commandClass, R request) {
    return applicationContext.getBean(commandClass).execute(request);
  }
}
