package portfolio.krabs.api.command;

import reactor.core.publisher.Mono;

public interface Command<R, T> {
  
  Mono<T> execute(R request);
}
