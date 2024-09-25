package com.mekami.common_domain.repository

import com.mekami.common_domain.PichuResult
import com.mekami.common_domain.entity.PokemonEntity

interface PokemonRepository {
    suspend fun getPokemon(
        id: Long,
    ): PichuResult<PokemonEntity>
}
