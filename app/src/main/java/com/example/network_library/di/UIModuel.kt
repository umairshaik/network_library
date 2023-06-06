package com.example.network_library.di

import com.example.network.di.WithInterceptor
import com.example.network_library.data.MovieRepo
import com.example.network_library.data.network.retrofit.MiniAppNetworkInstance
import com.example.network_library.domain.MovieUsecase
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
    fun provideMiniAppNetworkInstance(@WithInterceptor retrofit: Retrofit): MiniAppNetworkInstance {
        return MiniAppNetworkInstance(retrofit)
    }

    @Provides
    @Singleton
    fun provideMovieUsecase(movieRepo: MovieRepo): MovieUsecase {
        return MovieUsecase(movieRepo = movieRepo)
    }
}
