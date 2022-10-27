package com.hanna.waracle.test.data.api

sealed class ResponseWrapper<out T> {
    data class Success<out T>(val value: T): ResponseWrapper<T>()
    data class Error(val throwable: Throwable? = null) : ResponseWrapper<Nothing>()
}