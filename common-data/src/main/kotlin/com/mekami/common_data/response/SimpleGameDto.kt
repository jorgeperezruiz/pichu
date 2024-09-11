package com.mekami.common_data.response

import kotlinx.serialization.Serializable

@Serializable
data class SimpleGameDto(
    val name: String,
    val url: String,
)