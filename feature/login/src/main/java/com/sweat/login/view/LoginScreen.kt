package com.sweat.login.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.sweat.design_system.component.button.ButtonState
import com.sweat.design_system.component.button.SSBButton
import com.sweat.design_system.component.modifier.clickableSingle
import com.sweat.design_system.component.textfield.SSBTextField
import com.sweat.design_system.icon.EyeIcon
import com.sweat.design_system.theme.SSBAndroidTheme
import com.sweat.login.viewModel.LoginIntent
import com.sweat.login.viewModel.LoginSideEffect
import com.sweat.login.viewModel.LoginState
import com.sweat.login.viewModel.LoginViewModel

@Composable
fun LoginRoute(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToMain: () -> Unit,
    navigateToSignup: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(lifecycle) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.sideEffect.collect { sideEffect ->
                when (sideEffect) {
                    LoginSideEffect.LoginSuccess -> {
                        navigateToMain()
                    }

                    LoginSideEffect.LoginFailed -> {/*TODO()*/
                    }
                }
            }
        }
    }

    LoginScreen(
        modifier = modifier,
        state = state,
        onUsernameChange = { viewModel.handleIntent(LoginIntent.UpdateUsername(it)) },
        onPasswordChange = { viewModel.handleIntent(LoginIntent.UpdatePassword(it)) },
        onTogglePasswordVisibility = { viewModel.handleIntent(LoginIntent.TogglePasswordVisibility) },
        onLoginClick = {
            viewModel.handleIntent(LoginIntent.Login(state.username, state.password))
        },
        navigateToSignup = navigateToSignup
    )
}


@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    state: LoginState,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onTogglePasswordVisibility: () -> Unit,
    onLoginClick: () -> Unit,
    navigateToSignup: () -> Unit
) {

    SSBAndroidTheme { colors, typography ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(55.dp))

            Text(
                text = "Sweet-soaked-body",
                style = typography.label.copy(
                    fontFamily = FontFamily(Font(com.sweat.design_system.R.font.rookie))
                ),
                color = colors.main
            )
            Text(
                text = "함께 땀 흘리며 열정을 나눌 동료를 찾습니다",
                style = typography.bodySmall,
                color = colors.gray600
            )

            Spacer(modifier = Modifier.height(34.dp))

            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                SSBTextField(
                    textState = state.username,
                    label = "아이디",
                    placeHolder = "아이디를 입력해주세요",
                    onTextChange = onUsernameChange,
                    icon = {}
                )

                SSBTextField(
                    textState = state.password,
                    label = "비밀번호",
                    placeHolder = "비밀번호를 입력해주세요",
                    onTextChange = onPasswordChange,
                    icon = {
                        EyeIcon(
                            isSelected = state.isPasswordVisible,
                            modifier = Modifier.clickableSingle { onTogglePasswordVisibility() }
                        )
                    },
                    visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            SSBButton(
                modifier = Modifier.fillMaxWidth(),
                text = "다음",
                state = if (state.username.isNotEmpty() && state.password.isNotEmpty()) ButtonState.Enabled else ButtonState.Disabled,
                onClick = onLoginClick
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "아직 계정이 없으신가요?",
                style = typography.regularCaption,
                color = colors.gray300
            )

            Text(
                modifier = Modifier.clickableSingle { navigateToSignup() },
                text = "회원가입",
                style = typography.regularTextSM,
                color = colors.main
            )

            Spacer(modifier = Modifier.height(38.dp))
        }
    }
}