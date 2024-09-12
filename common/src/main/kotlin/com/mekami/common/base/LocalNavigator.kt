package com.mekami.common.base

import androidx.compose.runtime.compositionLocalOf
import com.mekami.common.navigation.FeatureNavigator

val LocalFeatureNavigator =
    compositionLocalOf<FeatureNavigator> {
        error("No LocalFeatureNavigator provided")
    }