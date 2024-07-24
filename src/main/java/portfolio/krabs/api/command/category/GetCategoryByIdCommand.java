package portfolio.krabs.api.command.category;

import portfolio.krabs.api.command.Command;
import portfolio.krabs.api.model.response.CategoryWebResponse;

public interface GetCategoryByIdCommand extends Command<String, CategoryWebResponse> {
}
