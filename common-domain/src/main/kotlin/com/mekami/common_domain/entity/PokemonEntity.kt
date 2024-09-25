package com.mekami.common_domain.entity

data class PokemonEntity(
    val id: Long,
    val height: Long,
    val moves: List<Mfe>?,
    val name: String,
    val spriteUrl: String,
    val weight: Long,
)

// TODO move out
data class Mfe(
    val move: Move,
    val versionGroupDetails: List<VersionGroupDetail>,
)

data class Move(
    val name: String,
    val url: String,
)

data class VersionGroupDetail(
    val levelLearnedAt: Long,
    val moveLearnMethod: MoveLearnMethod,
    val versionGroup: VersionGroup,
)

data class MoveLearnMethod(
    val name: String,
    val url: String,
)

data class VersionGroup(
    val name: String,
    val url: String,
)

