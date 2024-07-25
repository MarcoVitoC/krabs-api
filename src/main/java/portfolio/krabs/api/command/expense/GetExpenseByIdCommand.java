package portfolio.krabs.api.command.expense;

import portfolio.krabs.api.command.Command;
import portfolio.krabs.api.model.response.ExpenseWebResponse;

public interface GetExpenseByIdCommand extends Command<String, ExpenseWebResponse> {
}
