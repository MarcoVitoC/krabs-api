package portfolio.krabs.api.command.auth.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import portfolio.krabs.api.command.auth.LoginCommand;
import portfolio.krabs.api.helper.auth.AuthHelper;
import portfolio.krabs.api.model.request.AuthRequest;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class LoginCommandImpl implements LoginCommand {
  
  private final AuthenticationManager authenticationManager;
  
  private final AuthHelper authHelper;
  
  @Override
  public Mono<String> execute(AuthRequest request) {
    return Mono.fromSupplier(() -> request)
      .map(req -> getAuthentication(req.getUsername(), req.getPassword()))
      .map(authHelper::generateToken);
  }
  
  private Authentication getAuthentication(String username, String password) {
    return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
  }
  
}
