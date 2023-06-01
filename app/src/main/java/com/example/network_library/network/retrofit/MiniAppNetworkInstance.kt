package com.example.network_library.network.retrofit

import com.example.network_library.network.MiniAppDataSource
import com.example.network_library.network.di.createWebService
import com.example.network_library.network.model.Movies
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.serialization.Serializable
import retrofit2.Retrofit
import retrofit2.http.GET

@Serializable
data class NetworkResponse<T>(
    val data: T,
)

interface MiniAppApi {
    @GET(value = "movies")
    suspend fun getTrendingMovies(): NetworkResponse<List<Movies>>
}

@Singleton
class MiniAppNetworkInstance @Inject constructor(
    retrofit: Retrofit
) : MiniAppDataSource {

    private val networkApi: MiniAppApi = createWebService(retrofit)

    override suspend fun getMovies(): List<Movies> {
        return networkApi.getTrendingMovies().data
    }

}