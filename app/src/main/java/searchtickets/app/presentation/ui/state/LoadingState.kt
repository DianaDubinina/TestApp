package searchtickets.app.presentation.ui.state

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class LoadingState @Inject constructor() {
    val Loading = MutableLiveData<Boolean>()
}