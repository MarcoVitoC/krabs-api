package portfolio.krabs.api.command.category;

import portfolio.krabs.api.command.Command;
import portfolio.krabs.api.model.request.SaveOrUpdateCategoryRequest;

public interface UpdateCategoryCommand extends Command<SaveOrUpdateCategoryRequest, Boolean> {
}
