package searchtickets.app.presentation.ui.adapters

import searchtickets.app.domain.models.All
import searchtickets.app.presentation.ui.viewModels.ShowAllViewModel

class ShowAllAdapterDataSourceImpl(
    private val viewModel: ShowAllViewModel
) : ShowAllAdapterDataSource {
    override val info: List<All>
        get() = viewModel.info.value ?: emptyList()
}