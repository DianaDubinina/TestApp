package searchtickets.app.presentation.ui.viewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import searchtickets.app.domain.models.Info
import searchtickets.app.domain.usecases.GetImagesUseCase
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

    fun fetchData(fragmentContext: Context) {
        val id = "https://drive.usercontent.google.com/u/0/uc?id=1o1nX3uFISrG1gR-jr_03Qlu4_KEZWhav&export=download"
        _loadingState.isLoading.value = true
        viewModelScope.launch {
            try {
                val list = getImagesUseCase.invoke(id)
                _info.value = list
                _loadingState.isLoading.value = false
                Log.d("InfoViewModel", "Data updated: $list")

            } catch (e: Exception) {
                _loadingState.isLoading.value = false
                _errorState.getNoAvailableDialog.value = true
                Log.e("FETCH_IMAGES_ERROR", "An error occurred: ${e.message}")
            }
        }
    }
}


class LoadingState {
    val isLoading = MutableStateFlow<Boolean>(false)
}

class ErrorState {
    val getNoAvailableDialog = MutableStateFlow<Boolean>(false)
}