package com.mekami.pichu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.rememberNavController
import com.mekami.common.base.LocalFeatureNavigator
import com.mekami.common.compositions.LocalNavController
import com.mekami.common.navigation.FeatureNavigator
import com.mekami.pichu.navigation.MainNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            // Improvement: Create and use PichuTheme
            CompositionLocalProvider(
                LocalNavController provides navController,
                LocalFeatureNavigator provides FeatureNavigator(navController),
            ) {
                MainNavHost()
            }
        }
    }

}