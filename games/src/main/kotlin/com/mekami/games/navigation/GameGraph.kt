package com.mekami.games.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.mekami.common.navigation.GameDestinations
import com.mekami.common.navigation.navComposable
import com.mekami.games.screens.GameScreen

@SuppressLint("RestrictedApi")
@ExperimentalAnimationApi
fun NavGraphBuilder.gameGraph() {
    navigation(
        startDestination = GameDestinations.Detail().routeDestination,
        route = GameDestinations.Root.routeDestination,
    ) {
        deepLink(GameDestinations.Root.deepLinkDestination)

        navComposable(GameDestinations.Detail()) { GameScreen() }
    }
}
