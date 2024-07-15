package searchtickets.app.presentation.ui.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import searchtickets.app.domain.models.All
import searchtickets.app.domain.usecases.GetAllUseCase
import searchtickets.app.presentation.ui.state.ErrorState
import searchtickets.app.presentation.ui.state.LoadingState
import javax.inject.Inject

@HiltViewModel
class ShowAllViewModel @Inject constructor(
    private val getAlltIsUseCase: GetAllUseCase
) : ViewModel() {
    private val _info = MutableLiveData<List<All>>()
    val info: LiveData<List<All>> = _info

    private val _loadingState = LoadingState()
    val loadingState: LoadingState = _loadingState

    private val _errorState = ErrorState()
    val errorState: ErrorState = _errorState

    fun fetchData() {
        val id =
            "https://drive.google.com/uc?export=download&id=1HogOsz4hWkRwco4kud3isZHFQLUAwNBA"
        _loadingState.Loading.value = true
        viewModelScope.launch {
            try {
                val list = getAlltIsUseCase.invoke(id)
                _info.value = list
                _loadingState.Loading.value = false
                Log.d("ShowAllViewModel", "Data updated: $list")

            } catch (e: Exception) {
                _loadingState.Loading.value = false
                _errorState.getNoAvailableDialog.value = true
                Log.e("FETCH_SHOW_ERROR", "An error occurred: ${e.message}")
            }
        }
    }
}
