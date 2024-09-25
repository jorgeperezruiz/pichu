package com.mekami.common_domain.repository

import com.mekami.common_domain.PichuResult
import com.mekami.common_domain.entity.PokemonInListEntity

interface PokemonListRepository {
    suspend fun getPokemonList(): PichuResult<List<PokemonInListEntity>>
}
