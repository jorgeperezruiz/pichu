package com.mekami.common_data.mapper

import com.mekami.common_data.response.GameDto
import com.mekami.common_domain.BaseMapper
import com.mekami.common_domain.entity.GameEntity
import javax.inject.Inject

class GameMapper @Inject constructor(): BaseMapper<GameDto?, GameEntity>() {
    override fun mapFrom(from: GameDto?): GameEntity {
        return GameEntity(
            id = from?.id ?: throw IllegalArgumentException("Game missing id $from"),
            height = from.height,
            name = from.name,
            spriteUrl = from.sprites.frontDefault ?: "",
            moves = null,
            weight = from.weight,
        )
    }
}
