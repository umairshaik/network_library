package com.example.network_library.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.NetworkPayloadResponse
import com.example.network_library.data.model.Resources
import com.example.network_library.data.model.Series
import com.example.network_library.domain.MovieUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val usecase: MovieUsecase,
) : ViewModel() {

    val series = Series(resources = arrayListOf(Resources(pageStructureId = "1")))
    private val _uiState: MutableStateFlow<Series> = MutableStateFlow(series)
    val data: StateFlow<Series> = _uiState.asStateFlow()

    fun response() {
        viewModelScope.launch {
            when (val response = usecase.getMovie()) {
                is NetworkPayloadResponse.Error -> {
                    Log.i("MainActivityViewModel", "Error")
                }

                is NetworkPayloadResponse.Exception -> {
                    Log.i("MainActivityViewModel", "Exception")
                }

                is NetworkPayloadResponse.Success -> {
                    Log.i("MainActivityViewModel", response.data.toString())
                    _uiState.getAndUpdate { response.data }
                }
            }
        }
    }
}