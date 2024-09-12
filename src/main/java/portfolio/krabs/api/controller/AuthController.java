package portfolio.krabs.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth")
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class AuthController {
  
  //
}
