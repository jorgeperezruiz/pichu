package com.mekami.games.screens

import androidx.lifecycle.viewModelScope
import com.mekami.common.base.BaseViewModel
import com.mekami.common_data.provider.DispatcherProvider
import com.mekami.common_domain.PichuError
import com.mekami.common_domain.PichuResult
import com.mekami.common_domain.entity.PokemonInListEntity
import com.mekami.common_domain.usecase.GetAllPokemonsUseCase
import com.mekami.common_presentation.Action
import com.mekami.common_presentation.Effect
import com.mekami.common_presentation.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel
    @Inject
    constructor(
        private val getAllPokemonUseCase: GetAllPokemonsUseCase,
        private val dispatcherProvider: DispatcherProvider,
    ) : BaseViewModel<PokemonListState, PokemonListAction, PokemonListEffect>() {
        override fun createInitialScreenState() = PokemonListState(isLoading = true)

    init {
        loadData()
    }

    private fun loadData() {
        flow { emit(getAllPokemonUseCase.getGames()) }
            .onEach { result ->
                when (result) {
                    is PichuResult.Failure ->
                        setScreenState {
                            currentScreenState.copy(
                                isLoading = false,
                                error = result.error,
                            )
                        }
                    is PichuResult.Success ->
                        setScreenState {
                            currentScreenState.copy(
                                isLoading = false,
                                pokemonList = result.data,
                            )
                        }
                }
            }
            .flowOn(dispatcherProvider.io())
            .launchIn(viewModelScope)
    }

    override suspend fun handleActions(action: PokemonListAction) {
        when (action) {
            is PokemonListAction.OnPokeClick -> setEffect { PokemonListEffect.GoToGameScreen(action.id) }
            is PokemonListAction.OnTryAgain -> loadData()
        }
    }
}

data class PokemonListState(
    val isLoading: Boolean = false,
    val error: PichuError? = null,
    val pokemonList: List<PokemonInListEntity> = emptyList()
) : ScreenState

sealed class PokemonListAction : Action {
    data class OnPokeClick(val id: Long) : PokemonListAction()
    data object OnTryAgain : PokemonListAction()
}

sealed class PokemonListEffect : Effect {
    data class GoToGameScreen(val id: Long) : PokemonListEffect()
}
