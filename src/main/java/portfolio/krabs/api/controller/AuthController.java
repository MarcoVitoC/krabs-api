package portfolio.krabs.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import portfolio.krabs.api.command.auth.LoginCommand;
import portfolio.krabs.api.command.auth.RegisterCommand;
import portfolio.krabs.api.command.executor.CommandExecutor;
import portfolio.krabs.api.controller.util.ControllerUtil;
import portfolio.krabs.api.model.request.AuthRequest;
import portfolio.krabs.api.model.response.Response;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth")
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
public class AuthController {
  
  private final CommandExecutor commandExecutor;
  
  private final Scheduler scheduler;
  
  @PostMapping(value = "/register")
  public Mono<Response<Boolean>> register(@RequestBody AuthRequest request) {
    return ControllerUtil.doExecute(RegisterCommand.class, request, commandExecutor, scheduler);
  }
  
  @PostMapping(value = "/login")
  public Mono<Response<String>> login(@RequestBody AuthRequest request) {
    return ControllerUtil.doExecute(LoginCommand.class, request, commandExecutor, scheduler);
  }
}
