package com.mekami.games.screens

import com.mekami.common.base.BaseViewModel
import com.mekami.common_presentation.Action
import com.mekami.common_presentation.Effect
import com.mekami.common_presentation.ScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GamesViewModel
    @Inject
    constructor(
        // private val configUseCase: ConfigUseCase,
        // private val preferencesUseCase: PreferencesUseCase,
        // private val userUseCase: UserProvider,
        // private val analytics: AnalyticsProvider,
        // private val overrideNotificationsOnce: OverrideNotificationsUseCase,
        // private val dispatcherProvider: DispatcherProvider,
    ) : BaseViewModel<GamesState, GamesAction, GamesEffect>() {
        override fun createInitialScreenState() = GamesState
    override suspend fun handleActions(action: GamesAction) {
        TODO("Not yet implemented")
    }


}

object GamesState : ScreenState

sealed class GamesAction : Action {
    data object NoForceUpdate : GamesAction()

    data object ScreenShownToUser : GamesAction()
}

sealed class GamesEffect : Effect {
    data object LaunchMainActivity : GamesEffect()

    data object LaunchOnBoarding : GamesEffect()

    data class TryForceUpdate(val minVersionCode: Int) : GamesEffect()
}
