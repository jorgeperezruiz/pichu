package com.mekami.common_data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDto(
    @SerialName("height") val height: Long,
    val id: Long,
    val moves: List<Mfe>,
    val name: String,
    val order: Long,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Long,
)

@Serializable
data class Mfe(
    val move: Move,
    @SerialName("version_group_details")
    val versionGroupDetails: List<VersionGroupDetail>,
)

@Serializable
data class Move(
    val name: String,
    val url: String,
)

@Serializable
data class VersionGroupDetail(
    @SerialName("level_learned_at")
    val levelLearnedAt: Long,
    @SerialName("move_learn_method")
    val moveLearnMethod: MoveLearnMethod,
    @SerialName("version_group")
    val versionGroup: VersionGroup,
)

@Serializable
data class MoveLearnMethod(
    val name: String,
    val url: String,
)

@Serializable
data class VersionGroup(
    val name: String,
    val url: String,
)

@Serializable
data class Species(
    val name: String,
    val url: String,
)

@Serializable
data class Sprites(
    @SerialName("front_default")
    val frontDefault: String?,
)

data class Other(
    @SerialName("dream_world")
    val dreamWorld: DreamWorld,
    val home: Home,
    @SerialName("official-artwork")
    val officialArtwork: OfficialArtwork,
    val showdown: Showdown,
)

data class DreamWorld(
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_female")
    val frontFemale: Any?,
)

data class Home(
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_female")
    val frontFemale: Any?,
    @SerialName("front_shiny")
    val frontShiny: String,
    @SerialName("front_shiny_female")
    val frontShinyFemale: Any?,
)

data class OfficialArtwork(
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_shiny")
    val frontShiny: String,
)

data class Showdown(
    @SerialName("back_default")
    val backDefault: String,
    @SerialName("back_female")
    val backFemale: Any?,
    @SerialName("back_shiny")
    val backShiny: String,
    @SerialName("back_shiny_female")
    val backShinyFemale: Any?,
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_female")
    val frontFemale: Any?,
    @SerialName("front_shiny")
    val frontShiny: String,
    @SerialName("front_shiny_female")
    val frontShinyFemale: Any?,
)

data class Versions(
    @SerialName("generation-i")
    val generationI: GenerationI,
    @SerialName("generation-ii")
    val generationIi: GenerationIi,
    @SerialName("generation-iii")
    val generationIii: GenerationIii,
    @SerialName("generation-iv")
    val generationIv: GenerationIv,
    @SerialName("generation-v")
    val generationV: GenerationV,
    @SerialName("generation-vi")
    val generationVi: GenerationVi,
    @SerialName("generation-vii")
    val generationVii: GenerationVii,
    @SerialName("generation-viii")
    val generationViii: GenerationViii,
)

data class GenerationI(
    @SerialName("red-blue")
    val redBlue: RedBlue,
    val yellow: Yellow,
)

data class RedBlue(
    @SerialName("back_default")
    val backDefault: String,
    @SerialName("back_gray")
    val backGray: String,
    @SerialName("back_transparent")
    val backTransparent: String,
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_gray")
    val frontGray: String,
    @SerialName("front_transparent")
    val frontTransparent: String,
)

data class Yellow(
    @SerialName("back_default")
    val backDefault: String,
    @SerialName("back_gray")
    val backGray: String,
    @SerialName("back_transparent")
    val backTransparent: String,
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_gray")
    val frontGray: String,
    @SerialName("front_transparent")
    val frontTransparent: String,
)

data class GenerationIi(
    val crystal: Crystal,
    val gold: Gold,
    val silver: Silver,
)

data class Crystal(
    @SerialName("back_default")
    val backDefault: String,
    @SerialName("back_shiny")
    val backShiny: String,
    @SerialName("back_shiny_transparent")
    val backShinyTransparent: String,
    @SerialName("back_transparent")
    val backTransparent: String,
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_shiny")
    val frontShiny: String,
    @SerialName("front_shiny_transparent")
    val frontShinyTransparent: String,
    @SerialName("front_transparent")
    val frontTransparent: String,
)

data class Gold(
    @SerialName("back_default")
    val backDefault: String,
    @SerialName("back_shiny")
    val backShiny: String,
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_shiny")
    val frontShiny: String,
    @SerialName("front_transparent")
    val frontTransparent: String,
)

data class Silver(
    @SerialName("back_default")
    val backDefault: String,
    @SerialName("back_shiny")
    val backShiny: String,
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_shiny")
    val frontShiny: String,
    @SerialName("front_transparent")
    val frontTransparent: String,
)

data class GenerationIii(
    val emerald: Emerald,
    @SerialName("firered-leafgreen")
    val fireredLeafgreen: FireredLeafgreen,
    @SerialName("ruby-sapphire")
    val rubySapphire: RubySapphire,
)

data class Emerald(
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_shiny")
    val frontShiny: String,
)

data class FireredLeafgreen(
    @SerialName("back_default")
    val backDefault: String,
    @SerialName("back_shiny")
    val backShiny: String,
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_shiny")
    val frontShiny: String,
)

data class RubySapphire(
    @SerialName("back_default")
    val backDefault: String,
    @SerialName("back_shiny")
    val backShiny: String,
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_shiny")
    val frontShiny: String,
)

data class GenerationIv(
    @SerialName("diamond-pearl")
    val diamondPearl: DiamondPearl,
    @SerialName("heartgold-soulsilver")
    val heartgoldSoulsilver: HeartgoldSoulsilver,
    val platinum: Platinum,
)

data class DiamondPearl(
    @SerialName("back_default")
    val backDefault: String,
    @SerialName("back_female")
    val backFemale: Any?,
    @SerialName("back_shiny")
    val backShiny: String,
    @SerialName("back_shiny_female")
    val backShinyFemale: Any?,
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_female")
    val frontFemale: Any?,
    @SerialName("front_shiny")
    val frontShiny: String,
    @SerialName("front_shiny_female")
    val frontShinyFemale: Any?,
)

data class HeartgoldSoulsilver(
    @SerialName("back_default")
    val backDefault: String,
    @SerialName("back_female")
    val backFemale: Any?,
    @SerialName("back_shiny")
    val backShiny: String,
    @SerialName("back_shiny_female")
    val backShinyFemale: Any?,
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_female")
    val frontFemale: Any?,
    @SerialName("front_shiny")
    val frontShiny: String,
    @SerialName("front_shiny_female")
    val frontShinyFemale: Any?,
)

data class Platinum(
    @SerialName("back_default")
    val backDefault: String,
    @SerialName("back_female")
    val backFemale: Any?,
    @SerialName("back_shiny")
    val backShiny: String,
    @SerialName("back_shiny_female")
    val backShinyFemale: Any?,
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_female")
    val frontFemale: Any?,
    @SerialName("front_shiny")
    val frontShiny: String,
    @SerialName("front_shiny_female")
    val frontShinyFemale: Any?,
)

data class GenerationV(
    @SerialName("black-white")
    val blackWhite: BlackWhite,
)

data class BlackWhite(
    val animated: Animated,
    @SerialName("back_default")
    val backDefault: String,
    @SerialName("back_female")
    val backFemale: Any?,
    @SerialName("back_shiny")
    val backShiny: String,
    @SerialName("back_shiny_female")
    val backShinyFemale: Any?,
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_female")
    val frontFemale: Any?,
    @SerialName("front_shiny")
    val frontShiny: String,
    @SerialName("front_shiny_female")
    val frontShinyFemale: Any?,
)

data class Animated(
    @SerialName("back_default")
    val backDefault: String,
    @SerialName("back_female")
    val backFemale: Any?,
    @SerialName("back_shiny")
    val backShiny: String,
    @SerialName("back_shiny_female")
    val backShinyFemale: Any?,
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_female")
    val frontFemale: Any?,
    @SerialName("front_shiny")
    val frontShiny: String,
    @SerialName("front_shiny_female")
    val frontShinyFemale: Any?,
)

data class GenerationVi(
    @SerialName("omegaruby-alphasapphire")
    val omegarubyAlphasapphire: OmegarubyAlphasapphire,
    @SerialName("x-y")
    val xY: XY,
)

data class OmegarubyAlphasapphire(
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_female")
    val frontFemale: Any?,
    @SerialName("front_shiny")
    val frontShiny: String,
    @SerialName("front_shiny_female")
    val frontShinyFemale: Any?,
)

data class XY(
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_female")
    val frontFemale: Any?,
    @SerialName("front_shiny")
    val frontShiny: String,
    @SerialName("front_shiny_female")
    val frontShinyFemale: Any?,
)

data class GenerationVii(
    val icons: Icons,
    @SerialName("ultra-sun-ultra-moon")
    val ultraSunUltraMoon: UltraSunUltraMoon,
)

data class Icons(
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_female")
    val frontFemale: Any?,
)

data class UltraSunUltraMoon(
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_female")
    val frontFemale: Any?,
    @SerialName("front_shiny")
    val frontShiny: String,
    @SerialName("front_shiny_female")
    val frontShinyFemale: Any?,
)

data class GenerationViii(
    val icons: Icons2,
)

data class Icons2(
    @SerialName("front_default")
    val frontDefault: String,
    @SerialName("front_female")
    val frontFemale: Any?,
)

@Serializable
data class Stat(
    @SerialName("base_stat")
    val baseStat: Long,
    val effort: Long,
    val stat: Stat2,
)

@Serializable
data class Stat2(
    val name: String,
    val url: String,
)

@Serializable
data class Type(
    val slot: Long,
    val type: Type2,
)

@Serializable
data class Type2(
    val name: String,
    val url: String,
)