package searchtickets.app.presentation.ui.adapters

import searchtickets.app.domain.models.All

interface ShowAllAdapterDataSource {
    val info: List<All>
}