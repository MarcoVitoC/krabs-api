package portfolio.krabs.api.helper.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import portfolio.krabs.api.command.exception.CommandErrorException;
import portfolio.krabs.api.model.constant.Errors;
import portfolio.krabs.api.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
  
  private final UserRepository userRepository;
  
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username)
      .map(user -> User.withUsername(user.getUsername())
        .password(user.getPassword())
        .build())
      .orElseThrow(() -> CommandErrorException.withError(HttpStatus.BAD_REQUEST, Errors.INVALID_CREDENTIALS));
  }
  
}
