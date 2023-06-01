package com.example.network_library.network.di

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
    fun provideRetrofit(
        converterFactory: GsonConverterFactory,
        okHttpCallFactory: Call.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://www.google.com")//environment base URL
            .addConverterFactory(converterFactory)
            .callFactory(okHttpCallFactory)
            .build()
    }

}

inline fun <reified T> createWebService(retrofit: Retrofit): T = retrofit.create(T::class.java)