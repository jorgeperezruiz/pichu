package com.mekami.common_data.provider

import com.mekami.common_data.repository.PokemonRepositoryImpl
import com.mekami.common_data.repository.PokemonListRepositoryImpl
import com.mekami.common_domain.repository.PokemonRepository
import com.mekami.common_domain.repository.PokemonListRepository
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
    abstract fun bindGameRepository(impl: PokemonRepositoryImpl): PokemonRepository

    @Binds
    @Singleton
    abstract fun bindGamesRepository(impl: PokemonListRepositoryImpl): PokemonListRepository
}
