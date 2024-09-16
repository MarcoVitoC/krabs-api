package portfolio.krabs.api.command.auth;

import portfolio.krabs.api.command.Command;
import portfolio.krabs.api.model.request.AuthRequest;

public interface LoginCommand extends Command<AuthRequest, String> {
}
