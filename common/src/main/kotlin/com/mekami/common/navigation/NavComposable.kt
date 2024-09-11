package com.mekami.common.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

@SuppressLint("RestrictedApi")
@ExperimentalAnimationApi
fun NavGraphBuilder.navComposable(
    destination: Destination,
    content: @Composable (NavBackStackEntry) -> Unit,
) {
    val route = destination.routeDestinationWithParams
    val arguments =
        destination.params.map {
            navArgument(it.key) {
                type = NavType.inferFromValueType(it.value)
                defaultValue = it.value
                nullable = type.isNullableAllowed
            }
        }

    val deepLinks = listOf(navDeepLink { uriPattern = destination.deepLinkDestination })
    return composable(
        route,
        arguments,
        deepLinks,
        content,
    )
}

private fun isDifferentRoot(
    initDestination: NavDestination,
    targetDestination: NavDestination,
): Boolean {
    val initHierarchy = initDestination.hierarchy.toList()
    val lastInitElement = initHierarchy.lastIndex
    val targetHierarchy = targetDestination.hierarchy.toList()
    val lastTargetElement = targetHierarchy.lastIndex

    return initHierarchy.getOrNull(lastInitElement - 1)?.route != targetHierarchy.getOrNull(lastTargetElement - 1)?.route
}
