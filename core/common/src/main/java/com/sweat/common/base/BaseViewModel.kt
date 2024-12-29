package com.sweat.common.base

import android.util.Log
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
abstract class BaseViewModel<S, E, I>(initialState: S) : ViewModel() {
    private val _state: MutableStateFlow<S> = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val _sideEffect: MutableSharedFlow<E> = MutableSharedFlow()
    val sideEffect = _sideEffect.asSharedFlow()

    protected fun setState(update: S.() -> S) {
        Log.d("BaseViewModel", "setState called with: ${_state.value}")
        _state.update { it.update() }
    }

    protected fun postSideEffect(sideEffect: E) {
        Log.d("BaseViewModel", "postSideEffect called with: $sideEffect")
        viewModelScope.launch(Dispatchers.Main) {
            _sideEffect.emit(sideEffect)
        }
    }

    open fun handleIntent(intent: I) {
        // 기본적으로 로그를 찍음
        Log.d("BaseViewModel", "handleIntent called with: $intent")
    }
}