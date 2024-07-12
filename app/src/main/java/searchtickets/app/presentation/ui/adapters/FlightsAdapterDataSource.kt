package searchtickets.app.presentation.ui.adapters

import searchtickets.app.domain.models.Flights

interface FlightsAdapterDataSource {
    val info: List<Flights>
}