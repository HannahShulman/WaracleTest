package com.hanna.waracle.test.data.network

import com.google.gson.Gson
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit


internal class NetworkResponseCall<S : Any>(
    private val delegate: Call<S>,
) : Call<NetworkResponse<S>> {

    override fun enqueue(callback: Callback<NetworkResponse<S>>) {
        return delegate.enqueue(object : Callback<S> {
            override fun onResponse(call: Call<S>, response: Response<S>) {
                val body = response.body()

                if (response.isSuccessful) {
                    if (body != null) {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(NetworkResponse.Success(body))
                        )
                    } else {
                        // Response is successful but the body is null
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(NetworkResponse.Error(null))
                        )
                    }
                } else {
                    var throwableMessage: Throwable? = null
                    try {
                        val message: NetworkErrorMessage =
                            Gson().fromJson(
                                response.errorBody()?.string(),
                                NetworkErrorMessage::class.java
                            )
                        throwableMessage = Throwable(message.message)
                    } catch (e: Exception) {

                    }

                    callback.onResponse(
                        this@NetworkResponseCall,
                        Response.success(NetworkResponse.Error(throwableMessage))
                    )
                }
            }

            override fun onFailure(call: Call<S>, throwable: Throwable) {
                callback.onResponse(
                    this@NetworkResponseCall,
                    Response.success(NetworkResponse.Error(throwable))
                )
            }
        })
    }

    override fun isExecuted() = delegate.isExecuted

    override fun clone() = NetworkResponseCall(delegate.clone())

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<NetworkResponse<S>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun request(): Request = delegate.request()
    override fun timeout(): Timeout {
        return Timeout().timeout(10, TimeUnit.SECONDS)
    }
}