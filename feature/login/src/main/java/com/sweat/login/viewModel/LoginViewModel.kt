package com.sweat.login.viewModel

import androidx.lifecycle.viewModelScope
import com.sweat.common.base.BaseViewModel
import com.sweat.domain.auth.LoginRequestUseCase
import com.sweat.model.param.auth.LoginRequestParam
import com.sweat.network.api.AuthApi
import com.sweat.network.dto.auth.request.LoginRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRequestUseCase: LoginRequestUseCase
) : BaseViewModel<LoginState, LoginSideEffect, LoginIntent>(LoginState.getDefaultState()) {

    override fun handleIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.Login -> {
                setState {
                    copy(
                        username = intent.username,
                        password = intent.password
                    )
                }
                login(LoginRequestParam(username = intent.username, password = intent.password))
            }
            is LoginIntent.TogglePasswordVisibility -> {
                setState { copy(isPasswordVisible = !isPasswordVisible) }
            }
            is LoginIntent.UpdateUsername -> {
                setState { copy(username = intent.username) }
            }
            is LoginIntent.UpdatePassword -> {
                setState { copy(password = intent.password) }
            }
        }
    }

    fun login(
        body: LoginRequestParam
    ) {
        viewModelScope.launch {
            loginRequestUseCase(body = body).onSuccess {
                it.catch {

                }.collect {
                    postSideEffect(LoginSideEffect.LoginSuccess)
                }
            }.onFailure {
                postSideEffect(LoginSideEffect.LoginFailed("Login Failed"))
            }
        }
    }
}

data class LoginState(
    val username: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false
) {
    companion object {
        fun getDefaultState() = LoginState()
    }
}

sealed class LoginSideEffect {
    object LoginSuccess : LoginSideEffect()
    data class LoginFailed(val message: String) : LoginSideEffect()
}

sealed class LoginIntent {
    data class Login(val username: String, val password: String) : LoginIntent()
    data class UpdateUsername(val username: String) : LoginIntent()
    data class UpdatePassword(val password: String) : LoginIntent()
    object TogglePasswordVisibility : LoginIntent()
}
