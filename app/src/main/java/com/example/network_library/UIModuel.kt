package com.example.network_library

import com.example.network_library.data.MovieRepo
import com.example.network_library.data.MovieUsecase
import com.example.network_library.network.retrofit.MiniAppNetworkInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object UIModuel {

    @Provides
    @Singleton
    fun provideUsecase(miniAppNetworkInstance: MiniAppNetworkInstance): MovieRepo {
        return MovieRepo(miniAppNetworkInstance)
    }

    @Provides
    @Singleton
    fun provideMiniAppNetworkInstance(retrofit: Retrofit): MiniAppNetworkInstance {
        return MiniAppNetworkInstance(retrofit)
    }

    @Provides
    @Singleton
    fun provideMovieUsecase(movieRepo: MovieRepo): MovieUsecase {
        return MovieUsecase(movieRepo = movieRepo)
    }
}