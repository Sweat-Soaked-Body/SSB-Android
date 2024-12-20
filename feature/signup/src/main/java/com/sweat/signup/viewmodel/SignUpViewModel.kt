package com.sweat.signup.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.sweat.common.base.BaseViewModel
import com.sweat.domain.auth.SignUpRequestUseCase
import com.sweat.model.param.auth.SignUpRequestParam
import com.sweat.signup.enum.GenderEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpRequestUseCase
) : BaseViewModel<SignUpState, SignUpSideEffect, SignUpIntent>(SignUpState.getDefaultState()) {

    override fun handleIntent(intent: SignUpIntent) {
        when (intent) {
            is SignUpIntent.SignUp -> {
                signUp(
                    SignUpRequestParam(
                        username = intent.name,
                        name = intent.id,
                        password = intent.password,
                        sex = intent.gender.toString(),
                        age = intent.age.toInt(),
                        height = intent.height.toInt(),
                        weight = intent.weight.toInt()
                    )
                )
            }

            is SignUpIntent.OnNameTextStateChange -> setState { copy(name = intent.name) }

            is SignUpIntent.OnIdTextStateChange -> setState { copy(id = intent.id) }

            is SignUpIntent.OnPasswordTextStateChange -> setState { copy(password = intent.password) }

            is SignUpIntent.OnCheckPasswordTextStateChange -> setState { copy(checkPassword = intent.checkPassword) }

            is SignUpIntent.OnGenderSelected -> setState { copy(gender = intent.gender) }

            is SignUpIntent.OnAgeTextStateChange -> setState { copy(age = intent.age) }

            is SignUpIntent.OnHeightTextStateChange -> setState { copy(height = intent.height) }

            is SignUpIntent.OnWeightTextStateChange -> setState { copy(weight = intent.weight) }

            is SignUpIntent.OnCheckPasswordStateChange -> setState { copy(checkPasswordState = intent.checkPasswordState) }
        }
    }

    private fun signUp(body: SignUpRequestParam) {
        viewModelScope.launch {
            postSideEffect(SignUpSideEffect.Loading)
            signUpUseCase(body = body)
                .onSuccess {
                    it.catch {
                        postSideEffect(SignUpSideEffect.Failed)
                    }.collect {
                        postSideEffect(SignUpSideEffect.Success)
                    }
                }
                .onFailure {
                    postSideEffect(SignUpSideEffect.Failed)
                }
        }
    }
}

data class SignUpState(
    val name: String,
    val id: String,
    val password: String,
    val checkPassword: String,
    val gender: GenderEnum,
    val age: String,
    val height: String,
    val weight: String,
    val checkPasswordState: Boolean
) {
    companion object {
        fun getDefaultState() = SignUpState(
            name = "",
            id = "",
            password = "",
            checkPassword = "",
            gender = GenderEnum.unlabeled,
            age = "",
            height = "",
            weight = "",
            checkPasswordState = false
        )
    }
}

sealed interface SignUpSideEffect {
    object Loading : SignUpSideEffect
    object Success : SignUpSideEffect
    object Failed : SignUpSideEffect
}

sealed interface SignUpIntent {
    data class SignUp(
        val name: String,
        val id: String,
        val password: String,
        val gender: GenderEnum,
        val age: String,
        val height: String,
        val weight: String,
    ) : SignUpIntent

    data class OnNameTextStateChange(val name: String) : SignUpIntent
    data class OnIdTextStateChange(val id: String) : SignUpIntent
    data class OnPasswordTextStateChange(val password: String) : SignUpIntent
    data class OnCheckPasswordTextStateChange(val checkPassword: String) : SignUpIntent
    data class OnGenderSelected(val gender: GenderEnum) : SignUpIntent
    data class OnAgeTextStateChange(val age: String) : SignUpIntent
    data class OnHeightTextStateChange(val height: String) : SignUpIntent
    data class OnWeightTextStateChange(val weight: String) : SignUpIntent
    data class OnCheckPasswordStateChange(val checkPasswordState: Boolean) : SignUpIntent
}