package com.mekami.common_data.mapper

import com.mekami.common_data.response.SimpleGameDto
import com.mekami.common_domain.BaseMapper
import com.mekami.common_domain.entity.SimpleGameEntity
import javax.inject.Inject

class SimpleGameMapper @Inject constructor(): BaseMapper<SimpleGameDto?, SimpleGameEntity>() {
    override fun mapFrom(from: SimpleGameDto?): SimpleGameEntity {
        return SimpleGameEntity(
            id = from?.url?.split("/")?.lastOrNull { it.isNotEmpty() }?.toLongOrNull() ?: 0L,
            name = from?.name ?: "",
        )
    }
}
