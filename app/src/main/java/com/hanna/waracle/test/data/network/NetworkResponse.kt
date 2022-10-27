package com.hanna.waracle.test.data.network

//for convenience I have used just the 2 basic types of responses
sealed class NetworkResponse<out T : Any> {

    data class Success<T : Any>(val body: T) : NetworkResponse<T>()

    data class Error(val error: Throwable?) : NetworkResponse<Nothing>()
}