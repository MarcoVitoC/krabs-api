package portfolio.krabs.api.command.auth;

import portfolio.krabs.api.command.Command;

import java.security.Principal;

public interface GetUsernameCommand extends Command<Principal, String> {
}
