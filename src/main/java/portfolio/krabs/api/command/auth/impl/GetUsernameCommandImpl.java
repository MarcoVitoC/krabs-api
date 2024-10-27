package portfolio.krabs.api.command.auth.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import portfolio.krabs.api.command.auth.GetUsernameCommand;
import reactor.core.publisher.Mono;

import java.security.Principal;

@Component
@RequiredArgsConstructor
public class GetUsernameCommandImpl implements GetUsernameCommand {
  
  @Override
  public Mono<String> execute(Principal principal) {
    return Mono.fromSupplier(() -> principal)
      .thenReturn(principal.getName());
  }
  
}
