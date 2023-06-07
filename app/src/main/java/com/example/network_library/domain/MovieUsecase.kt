package com.example.network_library.domain

import com.example.network.NetworkPayloadResponse
import com.example.network_library.data.MovieRepo
import com.example.network_library.data.model.Movies
import com.example.network_library.data.model.Series
import javax.inject.Inject

class MovieUsecase @Inject constructor(private val movieRepo: MovieRepo) {

    suspend fun getMovie(): NetworkPayloadResponse<Movies> {
        return movieRepo.getMovies()
    }
}
