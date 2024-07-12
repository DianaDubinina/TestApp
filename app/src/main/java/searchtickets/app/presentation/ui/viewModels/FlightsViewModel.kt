package searchtickets.app.presentation.ui.viewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import searchtickets.app.domain.models.Flights
import searchtickets.app.domain.usecases.GetFlightsUseCase
import javax.inject.Inject

@HiltViewModel
class FlightsViewModel @Inject constructor(
    private val getFlightIsUseCase: GetFlightsUseCase
) : ViewModel() {
    private val _info = MutableLiveData<List<Flights>>()
    val info: LiveData<List<Flights>> = _info

    private val _loadingState = LoadingState()
    val loadingState: LoadingState = _loadingState

    private val _errorState = ErrorState()
    val errorState: ErrorState = _errorState

    fun fetchData(fragmentContext: Context) {
        val id =
            "https://drive.usercontent.google.com/u/0/uc?id=13WhZ5ahHBwMiHRXxWPq-bYlRVRwAujta&export=download"
        _loadingState.isLoading.value = true
        viewModelScope.launch {
            try {
                val list = getFlightIsUseCase.invoke(id)
                _info.value = list
                _loadingState.isLoading.value = false
                Log.d("FlightsViewModel", "Data updated: $list")

            } catch (e: Exception) {
                _loadingState.isLoading.value = false
                _errorState.getNoAvailableDialog.value = true
                Log.e("FETCH_FLIGHTS_ERROR", "An error occurred: ${e.message}")
            }
        }
    }
}
