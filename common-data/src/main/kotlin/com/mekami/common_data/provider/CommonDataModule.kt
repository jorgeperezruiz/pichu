package com.mekami.common_data.provider

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CommonDataModule {

    @Binds
    @Singleton
    abstract fun dispatcherProvider(dispatcherProviderImpl: DispatcherProviderImpl): DispatcherProvider
}
