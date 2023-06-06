package com.example.network_library.data

import com.example.network.di.NetworkPayloadResponse
import com.example.network_library.network.model.Movies
import com.example.network_library.network.retrofit.MiniAppNetworkInstance
import javax.inject.Inject

class MovieRepo @Inject constructor(private val networkInstance: MiniAppNetworkInstance) {
    suspend fun getMovies(): NetworkPayloadResponse<List<Movies>> {
        return networkInstance.getMovies()
    }
}

class MovieUsecase @Inject constructor(private val movieRepo: MovieRepo) {

    suspend fun getMovie(): NetworkPayloadResponse<List<Movies>> {
        return movieRepo.getMovies()
    }
}
