package com.mekami.common.compositions

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController

val LocalNavController =
    compositionLocalOf<NavHostController> {
        error("No LocalMainNavController provided")
    }
