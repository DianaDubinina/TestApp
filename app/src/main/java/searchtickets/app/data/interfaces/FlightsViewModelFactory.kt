package searchtickets.app.data.interfaces

import androidx.lifecycle.ViewModelProvider
import searchtickets.app.presentation.ui.viewModels.FlightsViewModel

interface FlightsViewModelFactory : ViewModelProvider.Factory {
    fun create(): FlightsViewModel
}