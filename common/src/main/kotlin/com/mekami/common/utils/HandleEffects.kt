package com.mekami.common.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.mekami.common_presentation.Effect
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

@Composable
fun <T : Effect> HandleEffects(
    effectFlow: Flow<T>,
    key: Any? = Unit,
    content: CoroutineScope.(effect: T) -> Unit,
) {
    LaunchedEffect(key) {
        effectFlow.collect { this.content(it) }
    }
}
