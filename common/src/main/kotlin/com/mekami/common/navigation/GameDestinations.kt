package com.mekami.common.navigation


sealed class GameDestinations : Destination {
    data object Root : GameDestinations() {
        override val routeDestination: String = "detail_root"
    }
    data class Detail(
        val id: String = "{$PARAM_GAME_ID}",
    ) : GameDestinations() {
        override val routeDestination: String = "detail"
        override val params: Map<String, Any>
            get() = mapOf(PARAM_GAME_ID to id)
    }

    companion object {
        const val PARAM_GAME_ID = "game_id"
    }
}
