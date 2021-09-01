package com.example.newsapp.utils

sealed class Result<T>(val isSuccess: Boolean) {
    class Success<T>(val data: T?) : Result<T>(true)
    class Error<T>(val throwable: Throwable? = null) : Result<T>(false)
}