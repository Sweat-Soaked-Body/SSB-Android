package com.sweat.signin.view.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sweat.design_system.theme.SSBTypography
import com.sweat.design_system.theme.color.SSBColor

@Composable
fun SigninTextField(
    modifier: Modifier,
    textState: String,
    placeHolder: String,
    helperText: String = "",
    isError: Boolean = false,
    onTextChange: (String) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BasicTextField(
            modifier = modifier.fillMaxWidth(),
            value = textState,
            onValueChange = { newText -> onTextChange(newText) },
            textStyle = SSBTypography.titleSmall.copy(
                textAlign = TextAlign.Center
            ),
            decorationBox = { innerTextField ->
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    if (textState.isEmpty()) {
                        Text(
                            text = placeHolder,
                            color = SSBColor.gray200,
                            style = SSBTypography.titleSmall,
                        )
                    }
                    innerTextField()
                }
            }
        )
        Text(
            text = helperText,
            color = if(isError) SSBColor.error else SSBColor.gray400,
            style = SSBTypography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SigninTextFieldPreview() {
    val (textState, onTextChange) = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.height(250.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        SigninTextField(
            modifier = Modifier.width(312.dp),
            placeHolder = "이름",
            textState = textState,
            onTextChange = onTextChange
        )

        SigninTextField(
            modifier = Modifier.width(312.dp),
            placeHolder = "이름",
            helperText = "실패",
            isError = true,
            textState = textState,
            onTextChange = onTextChange
        )

        SigninTextField(
            modifier = Modifier.width(312.dp),
            placeHolder = "이dldldlldlsdsdsddlld름",
            helperText = "이름 4~16",
            textState = textState,
            onTextChange = onTextChange
        )
    }
}