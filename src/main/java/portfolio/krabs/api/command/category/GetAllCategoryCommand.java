package portfolio.krabs.api.command.category;

import portfolio.krabs.api.command.Command;
import portfolio.krabs.api.model.request.EmptyRequest;
import portfolio.krabs.api.model.response.CategoryWebResponse;

import java.util.List;

public interface GetAllCategoryCommand extends Command<EmptyRequest, List<CategoryWebResponse>> {
}
