package com.mekami.common_data.mapper

import com.mekami.common_data.response.PokemonDto
import com.mekami.common_domain.BaseMapper
import com.mekami.common_domain.entity.PokemonEntity
import javax.inject.Inject

class PokemonMapper @Inject constructor(): BaseMapper<PokemonDto?, PokemonEntity>() {
    override fun mapFrom(from: PokemonDto?): PokemonEntity {
        return PokemonEntity(
            id = from?.id ?: throw IllegalArgumentException("Game missing id $from"),
            height = from.height,
            name = from.name,
            spriteUrl = from.sprites.frontDefault ?: "",
            moves = null,
            weight = from.weight,
        )
    }
}
