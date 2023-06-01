package com.example.network_library.data

import android.graphics.Movie
import com.example.network_library.network.model.Movies
import kotlinx.coroutines.flow.Flow

interface IMoviesRepo {
    fun getMovies(): Flow<List<Movies>>
}