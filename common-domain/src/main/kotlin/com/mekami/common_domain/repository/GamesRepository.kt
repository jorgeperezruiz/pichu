package com.mekami.common_domain.repository

import com.mekami.common_domain.PichuResult
import com.mekami.common_domain.entity.GameEntity
import com.mekami.common_domain.entity.SimpleGameEntity

interface GamesRepository {
    suspend fun getGames(): PichuResult<List<SimpleGameEntity>>
}
