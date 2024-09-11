package com.mekami.common_data

import android.util.Log
import com.mekami.common_domain.PichuError
import com.mekami.common_domain.PichuResult
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.HttpRequestTimeoutException
import java.net.UnknownHostException

suspend fun <T> safeCall(call: suspend () -> T): PichuResult<T> {
    return try {
        val result = call()
        PichuResult.Success(result)
    } catch (throwable: Throwable) {
        Log.e("Api error", throwable.message ?: "")
        if (isOffline(throwable)) {
            PichuResult.Failure(PichuError.Offline)
        } else {
            when (throwable) {
                is HttpRequestTimeoutException,
                is SocketTimeoutException,
                -> PichuResult.Failure(PichuError.Timeout)
                else -> PichuResult.Failure(PichuError.Unknown)
            }
        }
    }
}

fun isOffline(throwable: Throwable?): Boolean = throwable is UnknownHostException