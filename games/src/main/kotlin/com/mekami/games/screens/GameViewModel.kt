package com.mekami.games.screens

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.mekami.common.base.BaseViewModel
import com.mekami.common.navigation.GameDestinations.Companion.PARAM_GAME_ID
import com.mekami.common_data.provider.DispatcherProvider
import com.mekami.common_domain.PichuError
import com.mekami.common_domain.PichuResult
import com.mekami.common_domain.entity.GameEntity
import com.mekami.common_domain.entity.SimpleGameEntity
import com.mekami.common_domain.usecase.GetAllGamesUseCase
import com.mekami.common_domain.usecase.GetGameUseCase
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
class GameViewModel
    @Inject
    constructor(
        private val getGameUseCase: GetGameUseCase,
        private val dispatcherProvider: DispatcherProvider,
        savedStateHandle: SavedStateHandle,
    ) : BaseViewModel<GameState, GameAction, GameEffect>() {
        override fun createInitialScreenState() = GameState(isLoading = true)

    init {
        val id = savedStateHandle.get<String>(PARAM_GAME_ID)?.toLongOrNull() ?: -1
        flow { emit(getGameUseCase.getGameWithId(id)) }
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

    override suspend fun handleActions(action: GameAction) {
    }
}

data class GameState(
    val isLoading: Boolean = false,
    val error: PichuError? = null,
    val game: GameEntity? = null
) : ScreenState

sealed class GameAction : Action {
}

sealed class GameEffect : Effect {
}
