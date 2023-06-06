package com.example.network.di

import com.example.network.AuthInterceptor
import com.example.network.NetworkResponseAdapterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @WithoutInterceptor
    fun provideOkHttpClientWithInterceptor(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.HEADERS)
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        .build()

    @Provides
    @WithInterceptor
    fun provideOkHttpClientWithoutInterceptor(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.HEADERS)
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .addInterceptor(AuthInterceptor())
            .build()

    @Provides
    @Singleton
    fun provideGSon(): Gson = GsonBuilder().create()//or Gson()

    @Provides
    @Singleton
    fun gsonFactory() = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideNetworkResponseAdapterFactory() = NetworkResponseAdapterFactory()

    @Provides
    @WithInterceptor
    fun provideRetrofit(
        converterFactory: GsonConverterFactory,
        @WithInterceptor okHttpCallFactory: OkHttpClient,
        networkResponseAdapterFactory: NetworkResponseAdapterFactory
    ): Retrofit {
        // This is a builder pattern
        return Retrofit.Builder()
            .baseUrl("https://vmp-preprod.telkomsel.com/api/")//environment base URL
            .addConverterFactory(converterFactory)
            .client(okHttpCallFactory)
            .addCallAdapterFactory(networkResponseAdapterFactory)
            .build()
    }

    @Provides
    @WithoutInterceptor
    fun provideRetrofit2(
        converterFactory: GsonConverterFactory,
        @WithoutInterceptor okHttpCallFactory: OkHttpClient,
        networkResponseAdapterFactory: NetworkResponseAdapterFactory
    ): Retrofit {
        // This is a builder pattern
        return Retrofit.Builder()
            .baseUrl("https://vmp-preprod.telkomsel.com/api/")//environment base URL
            .addConverterFactory(converterFactory)
            .client(okHttpCallFactory)
            .addCallAdapterFactory(networkResponseAdapterFactory)
            .build()
    }

}


@Qualifier
annotation class WithInterceptor

@Qualifier
annotation class WithoutInterceptor

inline fun <reified T> createWebService(retrofit: Retrofit): T = retrofit.create(T::class.java)