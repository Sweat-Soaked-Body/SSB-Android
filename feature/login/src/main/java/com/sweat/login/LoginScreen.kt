package com.sweat.login

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sweat.design_system.component.button.ButtonState
import com.sweat.design_system.component.button.SSBButton
import com.sweat.design_system.component.modifier.clickableSingle
import com.sweat.design_system.component.textfield.SSBTextField
import com.sweat.design_system.icon.EyeIcon
import com.sweat.design_system.theme.SSBAndroidTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {
    val (idTextState, onIdTextChange) = remember { mutableStateOf("") }
    val (passwordTextState, onPasswordTextChange) = remember { mutableStateOf("") }
    val (isSelected, setIsSelected) = remember { mutableStateOf(false) }

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
                    textState = idTextState,
                    label = "아이디",
                    placeHolder = "아이디를 입력해주세요",
                    onTextChange = onIdTextChange,
                    icon = {}
                )

                SSBTextField(
                    textState = passwordTextState,
                    label = "비밀번호",
                    placeHolder = "비밀번호를 입력해주세요",
                    onTextChange = onPasswordTextChange,
                    icon = {
                        EyeIcon(
                            isSelected = isSelected,
                            modifier = Modifier.clickableSingle { setIsSelected(!isSelected) }
                        )
                    },
                    visualTransformation = if (isSelected) VisualTransformation.None else PasswordVisualTransformation()
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            SSBButton(
                modifier = Modifier.fillMaxWidth(),
                text = "다음",
                state = if (idTextState.isNotEmpty() && passwordTextState.isNotEmpty()) ButtonState.Enabled else ButtonState.Disabled,
                onClick = { /*TODO*/ }
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "아직 계정이 없으신가요?",
                style = typography.regularCaption,
                color = colors.gray300
            )

            Text(
                modifier = Modifier.clickableSingle { /*TODO*/ },
                text = "회원가입",
                style = typography.regularTextSM,
                color = colors.main
            )

            Spacer(modifier = Modifier.height(38.dp))
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}