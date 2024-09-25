package com.mekami.common_data.response

import kotlinx.serialization.Serializable

@Serializable
data class PokemonInListDto(
    val name: String,
    val url: String,
)