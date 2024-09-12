package com.mekami.pichu.navigation

import com.mekami.common.navigation.Destination


sealed class MainDestinations : Destination {
    data object Main : MainDestinations() {
        override val routeDestination: String = "main"
    }
}
