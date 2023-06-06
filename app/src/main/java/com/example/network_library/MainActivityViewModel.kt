package com.example.network_library

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.network.di.NetworkPayloadResponse
import com.example.network_library.data.MovieUsecase
import com.example.network_library.network.model.Movies
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
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState: MutableStateFlow<List<Movies>> = MutableStateFlow(emptyList())
    val data: StateFlow<List<Movies>> = _uiState.asStateFlow()

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
                     _uiState.getAndUpdate { it -> response.data }
                 }
             }
         }
    }
}