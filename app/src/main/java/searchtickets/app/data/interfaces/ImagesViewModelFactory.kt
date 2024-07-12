package searchtickets.app.data.interfaces

import androidx.lifecycle.ViewModelProvider
import searchtickets.app.presentation.ui.viewModels.InfoViewModel

interface ImagesViewModelFactory : ViewModelProvider.Factory {
    fun create(): InfoViewModel
}