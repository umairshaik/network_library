package com.example.network

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

sealed interface NetworkPayloadResponse<T> {
    data class Success<T>(val data: T) : NetworkPayloadResponse<T>
    data class Error<T>(val code: Int, val message: String?) : NetworkPayloadResponse<T>
    data class Exception<T>(val throwable: Throwable) : NetworkPayloadResponse<T>
}


class NetworkResponseAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type, annotations: Array<out Annotation>, retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (Call::class.java != getRawType(returnType)) return null
        check(returnType is ParameterizedType)

        val responseType = getParameterUpperBound(0, returnType)
        if (getRawType(responseType) != NetworkPayloadResponse::class.java) return null
        check(responseType is ParameterizedType)

        val successType = getParameterUpperBound(0, responseType)

        return NetworkResponseCallAdapter<Any>(successType)
    }
}

internal class NetworkResponseCallAdapter<T>(
    private val successType: Type,
) : CallAdapter<T, Call<NetworkPayloadResponse<T>>> {
    override fun responseType(): Type = successType

    override fun adapt(call: Call<T>): Call<NetworkPayloadResponse<T>> = NetworkResponseCall(call)
}

internal class NetworkResponseCall<T> constructor(
    private val callDelegate: Call<T>,
) : Call<NetworkPayloadResponse<T>> {

    override fun enqueue(callback: Callback<NetworkPayloadResponse<T>>) = callDelegate.enqueue(
        object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                response.body()?.let {
                    when (response.code()) {
                        in 200..208 -> {
                            callback.onResponse(
                                this@NetworkResponseCall,
                                Response.success(NetworkPayloadResponse.Success(it))
                            )
                        }

                        in 400..409 -> {
                            callback.onResponse(
                                this@NetworkResponseCall,
                                Response.success(
                                    NetworkPayloadResponse.Error(
                                        response.code(), response.message()
                                    )
                                ),
                            )
                        }
                    }
                } ?: callback.onResponse(
                    this@NetworkResponseCall,
                    Response.success(NetworkPayloadResponse.Error(123, "message"))
                )
            }

            override fun onFailure(call: Call<T>, throwable: Throwable) {
                callback.onResponse(
                    this@NetworkResponseCall,
                    Response.success(NetworkPayloadResponse.Exception(throwable))
                )
                call.cancel()
            }
        },
    )

    override fun clone(): Call<NetworkPayloadResponse<T>> =
        NetworkResponseCall(callDelegate.clone())

    override fun execute(): Response<NetworkPayloadResponse<T>> =
        throw UnsupportedOperationException("ResponseCall does not support execute.")

    override fun isExecuted(): Boolean = callDelegate.isExecuted

    override fun cancel() = callDelegate.cancel()

    override fun isCanceled(): Boolean = callDelegate.isCanceled

    override fun request(): Request = callDelegate.request()

    override fun timeout(): Timeout = callDelegate.timeout()
}


