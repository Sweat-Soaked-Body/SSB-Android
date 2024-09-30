package com.sweat.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
* @param S : State
* @param E : SideEffect
* @param I : Intent
*/
abstract class BaseViewModel<S, E, I>(initalState: S) : ViewModel() {
    private val _state: MutableStateFlow<S> = MutableStateFlow(initalState)
    val state = _state.asStateFlow()

    private val _sideEffect: MutableSharedFlow<E> = MutableSharedFlow()
    val sideEffect = _sideEffect.asSharedFlow()

    protected fun setState(newState: () -> S) {
        _state.update { newState() }
    }

    protected fun postSideEffect(sideEffect: E) {
        viewModelScope.launch(Dispatchers.IO) {
            _sideEffect.emit(sideEffect)
        }
    }

    abstract fun handleIntent(intent: I)
}