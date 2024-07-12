package searchtickets.app.presentation.ui.adapters

import searchtickets.app.domain.models.Info
import searchtickets.app.presentation.ui.viewModels.InfoViewModel

class ImagesAdapterDataSourceImpl(
    private val viewModel: InfoViewModel
) : ImagesAdapterDataSource {
    override val info: List<Info>
        get() = viewModel.info.value ?: emptyList()
}