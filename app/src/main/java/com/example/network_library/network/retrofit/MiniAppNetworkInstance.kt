package com.example.network_library.network.retrofit

import com.example.network.di.NetworkPayloadResponse
import com.example.network_library.network.MiniAppDataSource
import com.example.network_library.network.model.Movies
import javax.inject.Inject
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.http.GET

interface MiniAppApi {
    @GET(value = "movies")
    suspend fun getTrendingMovies(): NetworkPayloadResponse<List<Movies>>
}

@Singleton
class MiniAppNetworkInstance @Inject constructor(
    retrofit: Retrofit
) : MiniAppDataSource {

    private val networkApi: MiniAppApi = retrofit.create(MiniAppApi::class.java)

    override suspend fun getMovies(): NetworkPayloadResponse<List<Movies>> {
        return networkApi.getTrendingMovies()
    }

}