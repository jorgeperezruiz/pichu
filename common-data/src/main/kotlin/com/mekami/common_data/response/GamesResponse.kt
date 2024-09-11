package com.mekami.common_data.response

import kotlinx.serialization.Serializable

@Serializable
data class GamesResponse(
    val count: Int,
    val results: List<SimpleGameDto>,
)