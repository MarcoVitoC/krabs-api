package portfolio.krabs.api.command.auth.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import portfolio.krabs.api.command.auth.RegisterCommand;
import portfolio.krabs.api.command.exception.CommandErrorException;
import portfolio.krabs.api.entity.User;
import portfolio.krabs.api.model.constant.Errors;
import portfolio.krabs.api.model.request.AuthRequest;
import portfolio.krabs.api.repository.UserRepository;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RegisterCommandImpl implements RegisterCommand {
  
  private final UserRepository userRepository;
  
  private final PasswordEncoder passwordEncoder;
  
  @Override
  public Mono<Boolean> execute(AuthRequest request) {
    if (isNewUser(request.getUsername())) {
      register(request);
    }
    
    return Mono.just(Boolean.TRUE);
  }
  
  private Boolean isNewUser(String username) {
    if (userRepository.findByUsername(username).isPresent()) {
      throw CommandErrorException.withError(HttpStatus.CONFLICT, Errors.USERNAME_ALREADY_EXIST);
    }
    
    return Boolean.TRUE;
  }
  
  private void register(AuthRequest request) {
    userRepository.save(User.builder()
      .username(request.getUsername())
      .password(passwordEncoder.encode(request.getPassword()))
      .createdTime(LocalDateTime.now())
      .build());
  }
  
}
