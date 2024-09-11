package com.mekami.common_domain.repository

import com.mekami.common_domain.PichuResult
import com.mekami.common_domain.entity.GameEntity

interface GameRepository {
    suspend fun getGame(
        id: Long,
    ): PichuResult<GameEntity>
}
