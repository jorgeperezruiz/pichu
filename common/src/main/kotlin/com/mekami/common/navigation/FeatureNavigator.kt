package com.mekami.common.navigation

import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import androidx.navigation.NavController

class FeatureNavigator(private val navController: NavController) {
    fun openDeepLink(uri: String) {
        try {
            navigationToDestination(uri.toUri())
        } catch (e: IllegalArgumentException) {
            Log.e("FeatureNavigator", e.toString())
        }
    }

    fun openDestination(
        destination: Destination,
        removePrevious: Boolean = false,
    ) = when (removePrevious) {
        true -> navigationToDestinationCleaningBackStack(destination.routeDestinationWithParams)
        false -> navigationToDestination(destination.routeDestinationWithParams)
    }

    fun goBack() = navController.popBackStack()

    fun goBackTo(route: String) = navController.popBackStack(route, inclusive = false, saveState = true)

    private fun navigationToDestination(uri: Uri) = navController.navigate(uri)

    private fun navigationToDestinationCleaningBackStack(route: String) {
        navController.navigate(route) {
            navController.currentBackStackEntry?.destination?.route?.let {
                popUpTo(it) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }
        }
    }

    private fun navigationToDestination(route: String) = navController.navigate(route = route)
}
