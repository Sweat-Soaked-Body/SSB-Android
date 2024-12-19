package com.sweat.signup.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sweat.design_system.component.button.ButtonState
import com.sweat.design_system.component.button.SSBButton
import com.sweat.design_system.component.modifier.clickableSingle
import com.sweat.design_system.icon.ChevronLeftIcon
import com.sweat.design_system.theme.SSBTypography
import com.sweat.design_system.theme.color.SSBColor
import com.sweat.signup.component.SignUpTextField
import com.sweat.signup.enum.GenderEnum
import com.sweat.signup.viewmodel.SignUpIntent
import com.sweat.signup.viewmodel.SignUpState
import com.sweat.signup.viewmodel.SignUpViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
internal fun SignUpRoute(
    modifier: Modifier = Modifier,
    navigateToMain: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val signUpState by viewModel.state.collectAsStateWithLifecycle()

    SignUpScreen(
        modifier = modifier,
        navigateToMain = navigateToMain,
        signUpState = signUpState,
        signUpIntent = viewModel::handleIntent
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SignUpScreen(
    modifier: Modifier = Modifier,
    navigateToMain: () -> Unit,
    signUpState: SignUpState,
    signUpIntent: (SignUpIntent) -> Unit
) {
    val coroutine = rememberCoroutineScope()
    val pagerState = rememberPagerState { 9 }

    HorizontalPager(
        modifier = modifier.fillMaxSize(),
        state = pagerState,
        userScrollEnabled = false,
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            if (it != 8) {
                ChevronLeftIcon(modifier = modifier.clickableSingle {
                    if (pagerState.currentPage == 0) {
                        /*TODO*/
                    } else coroutine.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                })
                Spacer(modifier = modifier.height(16.dp))
            }
            when (it) {
                0 -> {
                    Text(
                        text = "만나서 반가워요!\n이름이 뭐예요?",
                        style = SSBTypography.titleSmall
                    )
                    Spacer(modifier = modifier.height(106.dp))

                    SignUpTextField(
                        modifier = modifier.fillMaxWidth(),
                        placeHolder = "이름",
                        textState = signUpState.name,
                        onTextChange = { name ->
                            signUpIntent(SignUpIntent.OnNameTextStateChange(name))
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                    )
                    Spacer(modifier = modifier.weight(1f))
                    SSBButton(
                        modifier = modifier.fillMaxWidth(),
                        text = "다음",
                        state = if (signUpState.name.isNotEmpty()) ButtonState.Enabled
                        else ButtonState.Disabled,
                        onClick = {
                            coroutine.launch {
                                pagerState.animateScrollToPage(1)
                            }
                        }
                    )
                }

                1 -> {
                    Text(
                        text = "아이디를 정해주세요!",
                        style = SSBTypography.titleSmall
                    )
                    Spacer(modifier = modifier.height(137.dp))

                    SignUpTextField(
                        modifier = modifier.fillMaxWidth(),
                        placeHolder = "아이디",
                        textState = signUpState.id,
                        onTextChange = { id ->
                            signUpIntent(SignUpIntent.OnIdTextStateChange(id))
                        },
                        helperText = "한글, 영어, 숫자 4~12자",
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                    )
                    Spacer(modifier = modifier.weight(1f))
                    SSBButton(
                        modifier = modifier.fillMaxWidth(),
                        text = "다음",
                        state = if (signUpState.id.isNotEmpty()) ButtonState.Enabled
                        else ButtonState.Disabled,
                        onClick = {
                            coroutine.launch {
                                pagerState.animateScrollToPage(2)
                            }
                        }
                    )
                }

                2 -> {
                    Text(
                        text = "쉿! 비밀번호를 적어주세요!",
                        style = SSBTypography.titleSmall
                    )
                    Spacer(modifier = modifier.height(137.dp))
                    SignUpTextField(
                        modifier = modifier.fillMaxWidth(),
                        placeHolder = "비밀번호",
                        textState = signUpState.password,
                        onTextChange = { password ->
                            signUpIntent(SignUpIntent.OnPasswordTextStateChange(password))
                        },
                        helperText = "영어, 숫자, 특수문자 1개 이상 8~24자",
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )
                    Spacer(modifier = modifier.weight(1f))
                    SSBButton(
                        modifier = modifier.fillMaxWidth(),
                        text = "다음",
                        state = if (signUpState.password.isNotEmpty()) ButtonState.Enabled
                        else ButtonState.Disabled,
                        onClick = {
                            coroutine.launch {
                                pagerState.animateScrollToPage(3)
                            }
                        }
                    )
                }

                3 -> {
                    Text(
                        text = "다시 한번 적어주세요!",
                        style = SSBTypography.titleSmall
                    )
                    Spacer(modifier = modifier.height(137.dp))
                    SignUpTextField(
                        modifier = modifier.fillMaxWidth(),
                        placeHolder = "비밀번호",
                        textState = signUpState.checkPassword,
                        onTextChange = { checkPassword ->
                            signUpIntent(SignUpIntent.OnCheckPasswordTextStateChange(checkPassword))
                        },
                        helperText = if (signUpState.checkPasswordState) "영어, 숫자, 특수문자 1개 이상 8~24자" else "일치하지 않은 비밀번호예요",
                        isError = !signUpState.checkPasswordState,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )
                    Spacer(modifier = modifier.weight(1f))
                    SSBButton(
                        modifier = modifier.fillMaxWidth(),
                        text = "다음",
                        state = if (signUpState.checkPassword.isNotEmpty()) ButtonState.Enabled
                        else ButtonState.Disabled,
                        onClick = {
                            coroutine.launch {
                                if (signUpState.password == signUpState.checkPassword) {
                                    SignUpIntent.OnCheckPasswordStateChange(checkPasswordState = true)
                                    pagerState.animateScrollToPage(4)
                                } else {
                                    SignUpIntent.OnCheckPasswordStateChange(checkPasswordState = false)
                                    pagerState.animateScrollToPage(3)
                                }
                            }
                        }
                    )
                }

                4 -> {
                    signUpState.gender.let {
                        coroutine.launch {
                            delay(700L)
                            signUpIntent(SignUpIntent.OnGenderSelected(GenderEnum.unlabeled))
                            pagerState.animateScrollToPage(5)
                        }
                    }

                    Text(
                        text = "성별을 알려주세요!",
                        style = SSBTypography.titleSmall
                    )
                    Spacer(modifier = modifier.height(137.dp))

                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            modifier = modifier
                                .clickableSingle {
                                    signUpIntent(SignUpIntent.OnGenderSelected(GenderEnum.male))
                                },
                            text = "남자",
                            style = SSBTypography.titleMedium,
                            color = if (signUpState.gender == GenderEnum.male) SSBColor.success else SSBColor.gray400
                        )
                        Spacer(modifier = modifier.width(90.dp))
                        Text(
                            modifier = modifier
                                .clickableSingle {
                                    signUpIntent(SignUpIntent.OnGenderSelected(GenderEnum.female))
                                },
                            text = "여자",
                            style = SSBTypography.titleMedium,
                            color = if (signUpState.gender == GenderEnum.female) SSBColor.error else SSBColor.gray400
                        )
                    }
                }

                5 -> {
                    Text(
                        text = "나이를 적어주세요!",
                        style = SSBTypography.titleSmall
                    )
                    Spacer(modifier = modifier.height(137.dp))

                    SignUpTextField(
                        modifier = modifier.fillMaxWidth(),
                        placeHolder = "나이",
                        textState = signUpState.age,
                        onTextChange = { age ->
                            signUpIntent(SignUpIntent.OnAgeTextStateChange(age))
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Spacer(modifier = modifier.weight(1f))
                    SSBButton(
                        modifier = modifier.fillMaxWidth(),
                        text = "다음",
                        state = if (signUpState.password.isNotEmpty()) ButtonState.Enabled
                        else ButtonState.Disabled,
                        onClick = {
                            coroutine.launch {
                                pagerState.animateScrollToPage(6)
                            }
                        }
                    )
                }

                6 -> {
                    Text(
                        text = "키를 적어주세요!",
                        style = SSBTypography.titleSmall
                    )
                    Spacer(modifier = modifier.height(137.dp))

                    SignUpTextField(
                        modifier = modifier.fillMaxWidth(),
                        placeHolder = "키",
                        textState = signUpState.height,
                        onTextChange = { height ->
                            signUpIntent(SignUpIntent.OnHeightTextStateChange(height))
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Spacer(modifier = modifier.weight(1f))
                    SSBButton(
                        modifier = modifier.fillMaxWidth(),
                        text = "다음",
                        state = if (signUpState.password.isNotEmpty()) ButtonState.Enabled
                        else ButtonState.Disabled,
                        onClick = {
                            coroutine.launch {
                                pagerState.animateScrollToPage(7)
                            }
                        }
                    )
                }

                7 -> {
                    Text(
                        text = "몸무게를 적어주세요!",
                        style = SSBTypography.titleSmall
                    )
                    Spacer(modifier = modifier.height(137.dp))

                    SignUpTextField(
                        modifier = modifier.fillMaxWidth(),
                        placeHolder = "몸무게",
                        textState = signUpState.weight,
                        onTextChange = { weight ->
                            signUpIntent(SignUpIntent.OnWeightTextStateChange(weight))
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Spacer(modifier = modifier.weight(1f))
                    SSBButton(
                        modifier = modifier.fillMaxWidth(),
                        text = "다음",
                        state = if (signUpState.password.isNotEmpty()) ButtonState.Enabled
                        else ButtonState.Disabled,
                        onClick = {
                            coroutine.launch {
                                pagerState.animateScrollToPage(8)
                            }
                        }
                    )
                }

                8 -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Spacer(modifier = modifier.height(255.dp))
                        Text(
                            text = "오은찬 님\n이제 같이 땀흘리러 가시죠! ",
                            style = SSBTypography.titleSmall
                        )
                        Spacer(modifier = modifier.weight(1f))
                        SSBButton(
                            modifier = modifier.fillMaxWidth(),
                            text = "시작하기",
                            state = if (signUpState.password.isNotEmpty()) ButtonState.Enabled
                            else ButtonState.Disabled,
                            onClick = navigateToMain
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SignupScreenPreview() {
    SignUpScreen(
        navigateToMain = {},
        signUpState = SignUpState.getDefaultState(),
        signUpIntent = {}
    )
}