package searchtickets.app.presentation.ui.adapters

import searchtickets.app.domain.models.Info

interface ImagesAdapterDataSource {
    val info: List<Info>
}