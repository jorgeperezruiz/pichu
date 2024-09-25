package com.mekami.common_data.repository

import com.mekami.common_data.PichuService
import com.mekami.common_data.mapper.PokemonMapper
import com.mekami.common_data.safeCall
import com.mekami.common_domain.PichuResult
import com.mekami.common_domain.entity.PokemonEntity
import com.mekami.common_domain.repository.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl
@Inject
constructor(
    private val service: PichuService,
    private val pokemonMapper: PokemonMapper,
) : PokemonRepository {
    private var results = listOf<PokemonEntity>()

    override suspend fun getPokemon(id: Long): PichuResult<PokemonEntity> =
        safeCall {
            val response = service.getPokemon(id)
            pokemonMapper.mapFrom(response)
        }

}
