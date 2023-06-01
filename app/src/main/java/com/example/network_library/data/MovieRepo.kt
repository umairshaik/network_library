package com.example.network_library.data

import com.example.network_library.network.model.Movies
import com.example.network_library.network.retrofit.MiniAppNetworkInstance
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepo @Inject constructor(private val networkInstance: MiniAppNetworkInstance): IMoviesRepo {
    override fun getMovies(): Flow<List<Movies>> {
       return flow { emit(networkInstance.getMovies())}
    }
}