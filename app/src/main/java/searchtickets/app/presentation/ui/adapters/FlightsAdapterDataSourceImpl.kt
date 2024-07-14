package searchtickets.app.presentation.ui.adapters

import searchtickets.app.domain.models.Flights
import searchtickets.app.presentation.ui.viewModels.FlightsViewModel

class FlightsAdapterDataSourceImpl(
    private val viewModel: FlightsViewModel
) : FlightsAdapterDataSource {
    override val info: List<Flights>
        get() = viewModel.info.value ?: emptyList()
}