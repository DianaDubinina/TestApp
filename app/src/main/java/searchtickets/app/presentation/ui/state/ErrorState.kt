package searchtickets.app.presentation.ui.state

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class ErrorState @Inject constructor() {
    val getNoAvailableDialog = MutableLiveData<Boolean>()
}