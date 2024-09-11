package com.mekami.common_data.provider

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DispatcherProviderImpl
    @Inject
    constructor() : DispatcherProvider {
        override fun ui(): CoroutineDispatcher = Dispatchers.Main

        override fun io(): CoroutineDispatcher = Dispatchers.IO

        override fun computation(): CoroutineDispatcher = Dispatchers.Default
    }
