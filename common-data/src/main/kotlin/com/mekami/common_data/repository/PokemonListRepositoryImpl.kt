package com.mekami.common_data.repository

import com.mekami.common_data.PichuService
import com.mekami.common_data.mapper.SimpleGameMapper
import com.mekami.common_data.safeCall
import com.mekami.common_domain.PichuResult
import com.mekami.common_domain.entity.PokemonInListEntity
import com.mekami.common_domain.repository.PokemonListRepository
import javax.inject.Inject

class PokemonListRepositoryImpl
@Inject
constructor(
    private val service: PichuService,
    private val gamesMapper: SimpleGameMapper,
) : PokemonListRepository {

    override suspend fun getPokemonList(): PichuResult<List<PokemonInListEntity>> =
        // Potential improvement: repository with cache.
        // Get from Room DB, then load api data and refresh if new data
        safeCall {
            val response = service.getPokemonList()
            gamesMapper.mapFromList(response.results)
        }
}
