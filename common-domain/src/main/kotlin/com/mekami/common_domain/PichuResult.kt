package com.mekami.common_domain


sealed class PichuResult<out T> {
    data class Success<out T>(val data: T) : PichuResult<T>()

    data class Failure<T>(val error: PichuError) : PichuResult<T>()
}

fun <T> T.toResult(): PichuResult<T> {
    return PichuResult.Success(this)
}

fun <T> PichuError.toResult(): PichuResult<T> {
    return PichuResult.Failure(this)
}

fun <T> PichuResult<T>.isSuccess(): Boolean {
    return this is PichuResult.Success
}

fun <T> PichuResult<T>.isFailure(): Boolean {
    return this is PichuResult.Failure
}

fun <T> PichuResult<T>.getData(): T {
    return (this as PichuResult.Success).data
}

fun <T> PichuResult<T>.getError(): PichuError {
    return (this as PichuResult.Failure).error
}

fun <T> PichuResult<T>.getOrElse(onFailure: (error: PichuError) -> T) =
    when (this) {
        is PichuResult.Success -> data
        is PichuResult.Failure -> onFailure(error)
    }

fun <T> PichuResult<T>.getOrNull(): T? =
    when (this) {
        is PichuResult.Success -> data
        is PichuResult.Failure -> null
    }

inline fun <R, T> PichuResult<T>.fold(
    onSuccess: (value: T) -> R,
    onFailure: (error: PichuError) -> R,
): R =
    when (this) {
        is PichuResult.Success -> onSuccess(data)
        is PichuResult.Failure -> onFailure(error)
    }

inline fun <R, T> PichuResult<T>.map(transform: (T) -> R): PichuResult<R> =
    when (this) {
        is PichuResult.Success -> PichuResult.Success(transform(data))
        is PichuResult.Failure -> PichuResult.Failure(error)
    }

inline fun <R, T> PichuResult<T>.flatMap(transform: (T) -> PichuResult<R>): PichuResult<R> =
    when (this) {
        is PichuResult.Success -> transform(data)
        is PichuResult.Failure -> PichuResult.Failure(error)
    }

inline fun <T> PichuResult<T>.onSuccess(onSuccess: (value: T) -> Unit): PichuResult<T> {
    if (this is PichuResult.Success) onSuccess(data)
    return this
}

inline fun <T> PichuResult<T>.onFailure(onFailure: (error: PichuError) -> Unit): PichuResult<T> {
    if (this is PichuResult.Failure) onFailure(error)
    return this
}
