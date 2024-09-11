package com.mekami.common_data.repository

import com.mekami.common_data.PichuService
import com.mekami.common_data.mapper.GameMapper
import com.mekami.common_data.safeCall
import com.mekami.common_domain.PichuResult
import com.mekami.common_domain.entity.GameEntity
import com.mekami.common_domain.repository.GameRepository
import javax.inject.Inject

class GameRepositoryImpl
@Inject
constructor(
    private val service: PichuService,
    private val gameMapper: GameMapper,
) : GameRepository {
    private var results = listOf<GameEntity>()

    override suspend fun getGame(id: Long): PichuResult<GameEntity> =
        safeCall {
            val response = service.getGame(id)
            gameMapper.mapFrom(response)
        }

}
