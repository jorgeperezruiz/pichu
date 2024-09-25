package com.mekami.common_data.response

import kotlinx.serialization.Serializable

@Serializable
data class PokemonListResponse(
    val count: Int,
    val results: List<PokemonInListDto>,
)