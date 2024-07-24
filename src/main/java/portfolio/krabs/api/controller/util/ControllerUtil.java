package portfolio.krabs.api.controller.util;

import portfolio.krabs.api.command.Command;
import portfolio.krabs.api.command.executor.CommandExecutor;
import portfolio.krabs.api.model.response.Response;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

public class ControllerUtil {
  
  public static <R, T> Mono<Response<T>> doExecute(
    Class<? extends Command<R, T>> commandClass, R request,
    CommandExecutor commandExecutor, Scheduler scheduler) {
    return commandExecutor.execute(commandClass, request)
      .map(Response::ok)
      .subscribeOn(scheduler);
  }
}
