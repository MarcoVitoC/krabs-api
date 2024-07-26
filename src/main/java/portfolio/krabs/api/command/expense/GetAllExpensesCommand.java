package portfolio.krabs.api.command.expense;

import portfolio.krabs.api.command.Command;
import portfolio.krabs.api.model.request.GetAllExpensesRequest;
import portfolio.krabs.api.model.response.ExpenseWebResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface GetAllExpensesCommand extends Command<GetAllExpensesRequest, Map<LocalDate, List<ExpenseWebResponse>>> {
}
