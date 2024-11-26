package com.ndc.sispak.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.concurrent.ConcurrentHashMap

abstract class BaseViewModel<State, Action, Effect>(
    private val state: State,
) : ViewModel() {
    private val _effect: Channel<Either<Effect>> = Channel(Channel.BUFFERED)
    val onEffect = _effect.receiveAsFlow()

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(state)
    val uiState = _uiState.asStateFlow()

    private val action: ConcurrentHashMap<String, suspend Action.(State) -> Unit> =
        ConcurrentHashMap()

    val instance: BaseViewModel<State, Action, Effect>
        get() = this

    init {
        this.onAction()
    }

    abstract fun onAction()

    @Suppress("UNCHECKED_CAST")
    fun <T : Action> on(name: Class<T>, cb: suspend T.(State) -> Unit) {
        if (this::class.java.simpleName.isNotEmpty()) {
            action[name.simpleName] = cb as suspend Action.(State) -> Unit
        }
    }

    fun updateState(cb: State.() -> State) {
        _uiState.tryEmit(cb(_uiState.value))
    }

    fun invokeAction(act: Action) {
        viewModelScope.launch {
            action[act!!::class.java.simpleName.orEmpty()]?.invoke(act, uiState.value)
        }
    }

    protected fun sendEffect(effect: Effect) = viewModelScope.launch {
        _effect.send(Either.right(effect))
        delay(100)
        _effect.send(Either.left())

    }

    protected fun effect(effect: Effect) = send(effect)

    infix fun BaseViewModel<State, Action, Effect>.send(effect: Effect) {
        sendEffect(effect)
    }
}