package com.mekami.pichu.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.mekami.common.compositions.LocalNavController
import com.mekami.common.navigation.navComposable
import com.mekami.games.navigation.gameGraph
import com.mekami.games.screens.PokemonListScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNavHost(modifier: Modifier = Modifier) {
    val navController = LocalNavController.current

    NavHost(
        navController = navController,
        startDestination = MainDestinations.Main.routeDestination,
        modifier = modifier,
    ) {
        navComposable(MainDestinations.Main) { PokemonListScreen() }
        gameGraph()
    }
}
