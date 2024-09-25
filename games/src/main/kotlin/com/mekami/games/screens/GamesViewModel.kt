package com.mekami.games.screens

import androidx.lifecycle.viewModelScope
import com.mekami.common.base.BaseViewModel
import com.mekami.common_data.provider.DispatcherProvider
import com.mekami.common_domain.PichuError
import com.mekami.common_domain.PichuResult
import com.mekami.common_domain.entity.SimpleGameEntity
import com.mekami.common_domain.usecase.GetAllGamesUseCase
import com.mekami.common_presentation.Action
import com.mekami.common_presentation.Effect
import com.mekami.common_presentation.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GamesViewModel
    @Inject
    constructor(
        private val getGamesUseCase: GetAllGamesUseCase,
        private val dispatcherProvider: DispatcherProvider,
    ) : BaseViewModel<GamesState, GamesAction, GamesEffect>() {
        override fun createInitialScreenState() = GamesState(isLoading = true)

    init {
        loadData()
    }

    private fun loadData() {
        flow { emit(getGamesUseCase.getGames()) }
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
                                games = result.data,
                            )
                        }
                }
            }
            .flowOn(dispatcherProvider.io())
            .launchIn(viewModelScope)
    }

    override suspend fun handleActions(action: GamesAction) {
        when (action) {
            is GamesAction.OnPokeClick -> setEffect { GamesEffect.GoToGameScreen(action.id) }
            is GamesAction.OnTryAgain -> loadData()
        }
    }
}

data class GamesState(
    val isLoading: Boolean = false,
    val error: PichuError? = null,
    val games: List<SimpleGameEntity> = emptyList()
) : ScreenState

sealed class GamesAction : Action {
    data class OnPokeClick(val id: Long) : GamesAction()
    data object OnTryAgain : GamesAction()
}

sealed class GamesEffect : Effect {
    data class GoToGameScreen(val id: Long) : GamesEffect()
}
