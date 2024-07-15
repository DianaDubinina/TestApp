package searchtickets.app.presentation.ui.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import searchtickets.app.domain.models.Info
import searchtickets.app.domain.usecases.GetImagesUseCase
import searchtickets.app.presentation.ui.state.ErrorState
import searchtickets.app.presentation.ui.state.LoadingState
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase
) : ViewModel() {
    private val _info = MutableLiveData<List<Info>>()
    val info: LiveData<List<Info>> = _info

    private val _loadingState = LoadingState()
    val loadingState: LoadingState = _loadingState

    private val _errorState = ErrorState()
    val errorState: ErrorState = _errorState

    fun fetchData() {
        val id =
            "https://drive.usercontent.google.com/u/0/uc?id=1o1nX3uFISrG1gR-jr_03Qlu4_KEZWhav&export=download"
        _loadingState.Loading.value = true
        viewModelScope.launch {
            try {
                val list = getImagesUseCase.invoke(id)
                _info.value = list
                _loadingState.Loading.value = false
                Log.d("InfoViewModel", "Data updated: $list")

            } catch (e: Exception) {
                _loadingState.Loading.value = false
                _errorState.getNoAvailableDialog.value = true
                Log.e("FETCH_IMAGES_ERROR", "An error occurred: ${e.message}")
            }
        }
    }
}
