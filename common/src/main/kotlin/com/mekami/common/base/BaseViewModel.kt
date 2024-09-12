package com.mekami.common.base


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mekami.common_presentation.Action
import com.mekami.common_presentation.Effect
import com.mekami.common_presentation.ScreenState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel<S : ScreenState, A : Action, E : Effect> : ViewModel() {
    private val initialScreenState: S by lazy { createInitialScreenState() }

    protected abstract fun createInitialScreenState(): S

    private val lastClickEvent: MutableStateFlow<Long> = MutableStateFlow(System.currentTimeMillis())

    protected val currentScreenState: S get() = screenState.value

    private val _screenState: MutableStateFlow<S> = MutableStateFlow(initialScreenState)
    val screenState = _screenState.asStateFlow()

    private val _actions: MutableSharedFlow<A> = MutableSharedFlow()
    protected val actions = _actions.asSharedFlow()

    private val _effect: Channel<E> = Channel()
    val effect = _effect.receiveAsFlow()

    protected abstract suspend fun handleActions(action: A)

    protected fun setScreenState(reduce: S.() -> S) {
        _screenState.value = currentScreenState.reduce()
    }

    init {
        viewModelScope.launch {
            actions.collect {
                handleActions(it)
            }
        }
    }

    fun setAction(action: A) = viewModelScope.launch { _actions.emit(action) }

    protected fun setEffect(builder: () -> E) = viewModelScope.launch { _effect.send(builder()) }

}
