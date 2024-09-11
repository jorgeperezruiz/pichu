package com.mekami.common_data.provider

import com.mekami.common_data.repository.GameRepositoryImpl
import com.mekami.common_data.repository.GamesRepositoryImpl
import com.mekami.common_domain.repository.GameRepository
import com.mekami.common_domain.repository.GamesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ContentModule {
    @Binds
    @Singleton
    abstract fun bindGameRepository(impl: GameRepositoryImpl): GameRepository

    @Binds
    @Singleton
    abstract fun bindGamesRepository(impl: GamesRepositoryImpl): GamesRepository
}
