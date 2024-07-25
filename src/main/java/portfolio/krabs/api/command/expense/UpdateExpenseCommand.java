package portfolio.krabs.api.command.expense;

import portfolio.krabs.api.command.Command;
import portfolio.krabs.api.model.request.SaveOrUpdateExpenseRequest;

public interface UpdateExpenseCommand extends Command<SaveOrUpdateExpenseRequest, Boolean> {
}
