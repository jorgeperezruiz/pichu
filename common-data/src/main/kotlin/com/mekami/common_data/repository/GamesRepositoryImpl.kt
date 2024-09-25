package com.mekami.common_data.repository

import com.mekami.common_data.PichuService
import com.mekami.common_data.mapper.GameMapper
import com.mekami.common_data.mapper.SimpleGameMapper
import com.mekami.common_data.safeCall
import com.mekami.common_domain.PichuResult
import com.mekami.common_domain.entity.GameEntity
import com.mekami.common_domain.entity.SimpleGameEntity
import com.mekami.common_domain.repository.GameRepository
import com.mekami.common_domain.repository.GamesRepository
import javax.inject.Inject

class GamesRepositoryImpl
@Inject
constructor(
    private val service: PichuService,
    private val gamesMapper: SimpleGameMapper,
) : GamesRepository {

    override suspend fun getGames(): PichuResult<List<SimpleGameEntity>> =
        // Potential improvement: repository with cache.
        // Get from Room DB, then load api data and refresh if new data
        safeCall {
            val response = service.getGames()
            gamesMapper.mapFromList(response.results)
        }
}
