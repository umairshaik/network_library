package com.example.network_library.network

import com.example.network_library.network.model.Movies

interface MiniAppDataSource {
    suspend fun getMovies(): List<Movies>
}