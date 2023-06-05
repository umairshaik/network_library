package com.example.network_library.network

import com.example.network.di.NetworkPayloadResponse
import com.example.network_library.network.model.Movies

interface MiniAppDataSource {
    suspend fun getMovies(): NetworkPayloadResponse<List<Movies>>
}