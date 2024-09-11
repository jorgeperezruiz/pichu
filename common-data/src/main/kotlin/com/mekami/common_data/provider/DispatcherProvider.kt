package com.mekami.common_data.provider

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    fun ui(): CoroutineDispatcher

    fun io(): CoroutineDispatcher

    fun computation(): CoroutineDispatcher
}
