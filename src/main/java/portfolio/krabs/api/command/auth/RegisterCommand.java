package portfolio.krabs.api.command.auth;

import portfolio.krabs.api.command.Command;
import portfolio.krabs.api.model.request.AuthRequest;

public interface RegisterCommand extends Command<AuthRequest, Boolean> {
}
