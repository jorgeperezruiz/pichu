package com.mekami.games.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mekami.common.base.LocalFeatureNavigator
import com.mekami.common.navigation.GameDestinations
import com.mekami.common.utils.HandleEffects
import com.mekami.common_domain.entity.PokemonInListEntity
import com.mekami.games.R

@Composable
fun PokemonListScreen(viewModel: PokemonListViewModel = hiltViewModel()) {

    val featureNavigator = LocalFeatureNavigator.current

    HandleEffects(effectFlow = viewModel.effect) {
        when (it) {
            is PokemonListEffect.GoToGameScreen -> featureNavigator.openDestination(
                GameDestinations.Detail(
                    it.id.toString()
                )
            )
        }
    }

    val state by viewModel.screenState.collectAsState()

    UiContent(state, viewModel::setAction)
}

@Composable
private fun UiContent(
    state: PokemonListState,
    action: (PokemonListAction) -> Unit
) {

    Scaffold(
        topBar = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Cyan)
                    .padding(vertical = 16.dp),
                text = stringResource(R.string.pokemons).uppercase(),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
            )
        },
    ) { paddingValues ->

        when {
            state.isLoading -> LoadingScreen(Modifier.padding(paddingValues))
            state.error != null -> ErrorMessage(Modifier.padding(paddingValues)) { action(PokemonListAction.OnTryAgain) }
            else -> GamesList(Modifier.padding(paddingValues), state.pokemonList, action)
        }
    }
}

@Composable
fun GamesList(
    modifier: Modifier = Modifier,
    games: List<PokemonInListEntity>,
    action: (PokemonListAction) -> Unit
) {

    LazyColumn(
        modifier = modifier,
        state = rememberLazyListState(),
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
            games.map {
                Text(
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .border(1.dp, color = Color.LightGray)
                        .clickable { action(PokemonListAction.OnPokeClick(it.id)) }
                        .padding(vertical = 16.dp),
                    text = it.name,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

