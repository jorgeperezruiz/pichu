package com.mekami.common_data.mapper

import com.mekami.common_data.response.PokemonInListDto
import com.mekami.common_domain.BaseMapper
import com.mekami.common_domain.entity.PokemonInListEntity
import javax.inject.Inject

class SimpleGameMapper @Inject constructor(): BaseMapper<PokemonInListDto?, PokemonInListEntity>() {
    override fun mapFrom(from: PokemonInListDto?): PokemonInListEntity {
        return PokemonInListEntity(
            id = from?.url?.split("/")?.lastOrNull { it.isNotEmpty() }?.toLongOrNull() ?: 0L,
            name = from?.name ?: "",
        )
    }
}
