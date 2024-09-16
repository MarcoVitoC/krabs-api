package portfolio.krabs.api.command.auth.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import portfolio.krabs.api.command.auth.LoginCommand;
import portfolio.krabs.api.model.request.AuthRequest;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class LoginCommandImpl implements LoginCommand {
  
  @Override
  public Mono<String> execute(AuthRequest request) {
    return null;
  }
  
}
