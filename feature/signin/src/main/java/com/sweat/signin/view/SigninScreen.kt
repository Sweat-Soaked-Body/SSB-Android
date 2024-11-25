package com.sweat.signin.view

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sweat.design_system.component.button.ButtonState
import com.sweat.design_system.component.button.SSBButton
import com.sweat.design_system.component.modifier.clickableSingle
import com.sweat.design_system.icon.ChevronLeftIcon
import com.sweat.design_system.theme.SSBTypography
import com.sweat.design_system.theme.color.SSBColor
import com.sweat.signin.view.component.SigninTextField
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SigninScreen(
    modifier: Modifier = Modifier
) {
    val (nameTextState, onNameTextChange) = remember { mutableStateOf("") }
    val (idTextState, onIdTextChange) = remember { mutableStateOf("") }
    val (passWordTextState, onPassWordTextChange) = remember { mutableStateOf("") }
    val (checkPassWordTextState, onCheckPassWordTextChange) = remember { mutableStateOf("") }
    val (selectedGender, onGenderSelected) = remember { mutableStateOf<String?>(null) }
    val (ageState, onAgeChange) = remember { mutableStateOf("") }
    val (heightState, onHeightChange) = remember { mutableStateOf("") }
    val (weightState, onWeightIChange) = remember { mutableStateOf("") }
    val (checkPassWord, onCheckPassWordChange) = remember { mutableStateOf(true) }
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
            if(it != 8) {
                ChevronLeftIcon(modifier = modifier.clickableSingle {
                    if (pagerState.currentPage == 0) {/*TODO*/
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

                    SigninTextField(
                        modifier = modifier.fillMaxWidth(),
                        placeHolder = "이름",
                        textState = nameTextState,
                        onTextChange = onNameTextChange
                    )
                    Spacer(modifier = modifier.weight(1f))
                    SSBButton(
                        modifier = modifier.fillMaxWidth(),
                        text = "다음",
                        state = if (nameTextState.isNotEmpty()) ButtonState.Enabled
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

                    SigninTextField(
                        modifier = modifier.fillMaxWidth(),
                        placeHolder = "아이디",
                        textState = idTextState,
                        onTextChange = onIdTextChange,
                        helperText = "한글, 영어, 숫자 4~12자"
                    )
                    Spacer(modifier = modifier.weight(1f))
                    SSBButton(
                        modifier = modifier.fillMaxWidth(),
                        text = "다음",
                        state = if (idTextState.isNotEmpty()) ButtonState.Enabled
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

                    SigninTextField(
                        modifier = modifier.fillMaxWidth(),
                        placeHolder = "비밀번호",
                        textState = passWordTextState,
                        onTextChange = onPassWordTextChange,
                        helperText = "영어, 숫자, 특수문자 1개 이상 8~24자"
                    )
                    Spacer(modifier = modifier.weight(1f))
                    SSBButton(
                        modifier = modifier.fillMaxWidth(),
                        text = "다음",
                        state = if (passWordTextState.isNotEmpty()) ButtonState.Enabled
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

                    SigninTextField(
                        modifier = modifier.fillMaxWidth(),
                        placeHolder = "비밀번호",
                        textState = checkPassWordTextState,
                        onTextChange = onCheckPassWordTextChange,
                        helperText = if(checkPassWord) "영어, 숫자, 특수문자 1개 이상 8~24자" else "일치하지 않은 비밀번호예요",
                        isError = !checkPassWord
                    )
                    Spacer(modifier = modifier.weight(1f))
                    SSBButton(
                        modifier = modifier.fillMaxWidth(),
                        text = "다음",
                        state = if (checkPassWordTextState.isNotEmpty()) ButtonState.Enabled
                        else ButtonState.Disabled,
                        onClick = {
                            coroutine.launch {
                                if (checkPassWordTextState == passWordTextState) {
                                    onCheckPassWordChange(true)
                                    pagerState.animateScrollToPage(4)
                                }
                                else {
                                    onCheckPassWordChange(false)
                                    pagerState.animateScrollToPage(3)
                                }
                            }
                        }
                    )
                }

                4 -> {
                    selectedGender?.let {
                        coroutine.launch {
                            kotlinx.coroutines.delay(700)
                            onGenderSelected("")
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
                                    onGenderSelected("남자")
                                },
                            text = "남자",
                            style = SSBTypography.titleMedium,
                            color = if (selectedGender == "남자") SSBColor.success else SSBColor.gray400
                        )
                        Spacer(modifier = modifier.width(90.dp))
                        Text(
                            modifier = modifier
                                .clickableSingle {
                                    onGenderSelected("여자")
                                },
                            text = "여자",
                            style = SSBTypography.titleMedium,
                            color = if (selectedGender == "여자") SSBColor.error else SSBColor.gray400
                        )
                    }
                }

                5 -> {
                    Text(
                        text = "나이를 적어주세요!",
                        style = SSBTypography.titleSmall
                    )
                    Spacer(modifier = modifier.height(137.dp))

                    SigninTextField(
                        modifier = modifier.fillMaxWidth(),
                        placeHolder = "나이",
                        textState = ageState,
                        onTextChange = onAgeChange,
                    )
                    Spacer(modifier = modifier.weight(1f))
                    SSBButton(
                        modifier = modifier.fillMaxWidth(),
                        text = "다음",
                        state = if (passWordTextState.isNotEmpty()) ButtonState.Enabled
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

                    SigninTextField(
                        modifier = modifier.fillMaxWidth(),
                        placeHolder = "키",
                        textState = heightState,
                        onTextChange = onHeightChange,
                    )
                    Spacer(modifier = modifier.weight(1f))
                    SSBButton(
                        modifier = modifier.fillMaxWidth(),
                        text = "다음",
                        state = if (passWordTextState.isNotEmpty()) ButtonState.Enabled
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

                    SigninTextField(
                        modifier = modifier.fillMaxWidth(),
                        placeHolder = "몸무게",
                        textState = weightState,
                        onTextChange = onWeightIChange,
                    )
                    Spacer(modifier = modifier.weight(1f))
                    SSBButton(
                        modifier = modifier.fillMaxWidth(),
                        text = "다음",
                        state = if (passWordTextState.isNotEmpty()) ButtonState.Enabled
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
                            state = if (passWordTextState.isNotEmpty()) ButtonState.Enabled
                            else ButtonState.Disabled,
                            onClick = { /*TODO*/ }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SigninScreenPreview() {
    SigninScreen()
}