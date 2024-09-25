package com.mekami.common_domain.usecase

import com.mekami.common_domain.repository.PokemonRepository
import javax.inject.Inject

class GetPokemonUseCase
    @Inject
    constructor(
        private val pokemonRepo: PokemonRepository,
    ) {
        suspend fun getPokemonWithId(
            id: Long,
        ) = pokemonRepo.getPokemon(id)
    }
