package com.example.network

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request().apply {
            Log.i("OKHTTP", "attaching interceptor")
            this.newBuilder()
                .header("Authorization", "Bearer abc").build()
        })

    }
}