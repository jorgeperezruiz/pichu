package com.mekami.games.screens

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.mekami.common.base.BaseViewModel
import com.mekami.common.navigation.GameDestinations.Companion.PARAM_GAME_ID
import com.mekami.common_data.provider.DispatcherProvider
import com.mekami.common_domain.PichuError
import com.mekami.common_domain.PichuResult
import com.mekami.common_domain.entity.PokemonEntity
import com.mekami.common_domain.usecase.GetPokemonUseCase
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
class PokemonDetailViewModel
    @Inject
    constructor(
        private val getPokemonUseCase: GetPokemonUseCase,
        private val dispatcherProvider: DispatcherProvider,
        private val savedStateHandle: SavedStateHandle,
    ) : BaseViewModel<PokemonState, PokemonAction, PokemonEffect>() {
        override fun createInitialScreenState() = PokemonState(isLoading = true)

    init {
        loadData()
    }

    private fun loadData() {
        val id = savedStateHandle.get<String>(PARAM_GAME_ID)?.toLongOrNull() ?: -1
        flow { emit(getPokemonUseCase.getPokemonWithId(id)) }
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
                                game = result.data,
                            )
                        }
                }
            }
            .flowOn(dispatcherProvider.io())
            .launchIn(viewModelScope)
    }

    override suspend fun handleActions(action: PokemonAction) {
        when (action) {
            is PokemonAction.OnTryAgain -> loadData()
        }
    }
}

data class PokemonState(
    val isLoading: Boolean = false,
    val error: PichuError? = null,
    val game: PokemonEntity? = null
) : ScreenState

sealed class PokemonAction : Action {
    data object OnTryAgain : PokemonAction()
}

sealed class PokemonEffect : Effect {
}
