package com.mekami.games.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mekami.common.utils.HandleEffects
import com.mekami.common_domain.entity.PokemonEntity
import com.mekami.games.R

@Composable
fun PokemonDetailScreen(viewModel: PokemonDetailViewModel = hiltViewModel()) {

    HandleEffects(effectFlow = viewModel.effect) {}

    val state by viewModel.screenState.collectAsState()

    UiContent(state, viewModel::setAction)
}

@Composable
private fun UiContent(
    state: PokemonState,
    action: (PokemonAction) -> Unit
) {
    when {
        state.isLoading -> LoadingScreen()
        state.error != null -> ErrorMessage { action(PokemonAction.OnTryAgain) }
        state.game != null -> PokeScreen(state.game)
    }
}

@Composable
private fun PokeScreen(game: PokemonEntity) {
    Scaffold(
        topBar = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Cyan)
                    .padding(vertical = 16.dp),
                text = game.name.uppercase(),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
            )
        },
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    modifier = Modifier.size(200.dp),
                    model = game.spriteUrl,
                    contentDescription = null,
                )
                Column(verticalArrangement = Arrangement.SpaceAround) {
                    Text(
                        modifier = Modifier,
                        text = stringResource(R.string.number) + " " + game.id,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        modifier = Modifier,
                        text = stringResource(R.string.height) + " " + game.height,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        modifier = Modifier,
                        text = stringResource(R.string.weight) + " " + game.weight,
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}
