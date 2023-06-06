package com.example.network.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun okHttpCallFactory(): Call.Factory = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
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
    @Singleton
    fun provideRetrofit(
        converterFactory: GsonConverterFactory,
        okHttpCallFactory: Call.Factory,
        networkResponseAdapterFactory: NetworkResponseAdapterFactory
    ): Retrofit {
        // This is a builder pattern
        return Retrofit.Builder()
            .baseUrl("http://localhost:3000")//environment base URL
            .addConverterFactory(converterFactory)
            .callFactory(okHttpCallFactory)
            .addCallAdapterFactory(networkResponseAdapterFactory)
            .build()
    }

}

inline fun <reified T> createWebService(retrofit: Retrofit): T = retrofit.create(T::class.java)