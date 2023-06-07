package com.example.network_library.data

import com.example.network.NetworkPayloadResponse
import com.example.network_library.data.model.Movies
import com.example.network_library.data.model.Series
import com.example.network_library.data.network.retrofit.MiniAppNetworkInstance
import javax.inject.Inject

class MovieRepo @Inject constructor(private val networkInstance: MiniAppNetworkInstance) {
    suspend fun getMovies(): NetworkPayloadResponse<Movies> {
        return networkInstance.getMovies()
    }
}

