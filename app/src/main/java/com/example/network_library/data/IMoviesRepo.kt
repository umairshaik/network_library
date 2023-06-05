package com.example.network_library.data

import com.example.network.di.NetworkPayloadResponse
import com.example.network_library.network.model.Movies

interface IMoviesRepo {
    suspend fun getMovies(): NetworkPayloadResponse<List<Movies>>
}