package com.mekami.games.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mekami.common.base.LocalFeatureNavigator
import com.mekami.common.utils.HandleEffects
import com.mekami.games.R

@Composable
fun GameScreen(viewModel: GameViewModel = hiltViewModel()) {

    HandleEffects(effectFlow = viewModel.effect) {}

    val state by viewModel.screenState.collectAsState()

    UiContent(state, viewModel::setAction)
}

@Composable
private fun UiContent(
    state: GameState,
    action: (GameAction) -> Unit) {
    // TODO Show error if error not null
    state.game?.let {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                text = it.name.uppercase(),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    modifier = Modifier.size(200.dp),
                    model = it.spriteUrl,
                    contentDescription = null,
                )
                Column(verticalArrangement = Arrangement.SpaceAround) {
                    Text(
                        modifier = Modifier,
                        text = stringResource(R.string.number) + " " + it.id,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        modifier = Modifier,
                        text = stringResource(R.string.height) + " " + it.height,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        modifier = Modifier,
                        text = stringResource(R.string.weight) + " " + it.weight,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }

    }
}
