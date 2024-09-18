package portfolio.krabs.api.helper.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthHelper {
  
  private final JwtEncoder jwtEncoder;

  public String generateToken(Authentication authentication) {
    String scope = authentication.getAuthorities().stream()
      .map(GrantedAuthority::getAuthority)
      .collect(Collectors.joining(" "));
    
    JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
      .issuer("self")
      .issuedAt(Instant.now())
      .expiresAt(Instant.now().plus(10, ChronoUnit.HOURS))
      .subject(authentication.getName())
      .claim("scope", scope)
      .build();
    
    return jwtEncoder.encode(
      JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS512).build(),
        jwtClaimsSet))
      .getTokenValue();
  }

}