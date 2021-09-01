package com.example.newsapp.utils

import io.reactivex.Single
import io.reactivex.functions.Function


fun <T : Any, R : Any> Single<T>.convertResponseToResult(transform: (T?) -> R?): Single<Result<R>> {
    return this.map(Mapper(transform = transform))
        .onErrorReturn {
            return@onErrorReturn Result.Error(throwable = it)
        }
}


class Mapper<T: Any, R: Any>(val transform: (T?) -> R?) : Function<T, Result<R>> {
    override fun apply(response: T): Result<R> {
        return Result.Success(transform(response))
    }
}