package com.mekami.common_domain.usecase

import com.mekami.common_domain.repository.PokemonListRepository
import javax.inject.Inject

class GetAllPokemonsUseCase
    @Inject
    constructor(
        private val gamesRepo: PokemonListRepository,
    ) {
        suspend fun getGames() = gamesRepo.getPokemonList()
    }
