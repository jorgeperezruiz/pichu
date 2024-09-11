package com.mekami.games.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mekami.common.utils.HandleEffects
import com.mekami.games.R

@Composable
fun GamesScreen(viewModel: GamesViewModel = hiltViewModel()) {

    HandleEffects(effectFlow = viewModel.effect) {
        when (it) {
            GamesEffect.LaunchMainActivity -> TODO()
            GamesEffect.LaunchOnBoarding -> TODO()
            is GamesEffect.TryForceUpdate -> TODO()
        }
    }

    UiContent()
}

@Composable
private fun UiContent() {
    Box(
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.padding(top = 150.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(290.dp))
            Text(
                text = stringResource(id = R.string.games).uppercase(),
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.labelMedium,
            )
        }
    }
}
