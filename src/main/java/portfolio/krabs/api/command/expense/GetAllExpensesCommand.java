package portfolio.krabs.api.command.expense;

import portfolio.krabs.api.command.Command;
import portfolio.krabs.api.model.request.GetAllExpensesRequest;
import portfolio.krabs.api.model.response.ExpenseWebResponse;

import java.util.List;

public interface GetAllExpensesCommand extends Command<GetAllExpensesRequest, List<ExpenseWebResponse>> {
}
