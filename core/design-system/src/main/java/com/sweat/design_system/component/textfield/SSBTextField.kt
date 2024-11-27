package com.sweat.design_system.component.textfield

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sweat.design_system.component.modifier.clickableSingle
import com.sweat.design_system.icon.EyeIcon
import com.sweat.design_system.theme.SSBTypography
import com.sweat.design_system.theme.color.SSBColor

@Composable
fun SSBTextField(
    modifier: Modifier = Modifier,
    textState: String,
    label: String,
    placeHolder: String,
    helperText: String = "",
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onTextChange: (String) -> Unit,
    icon: @Composable () -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = label,
            color = if (isError) SSBColor.error else Color.Black,
            style = SSBTypography.bodySmall
        )

        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = if (isError) SSBColor.error else SSBColor.gray100,
                    shape = RoundedCornerShape(size = 8.dp)
                )
                .padding(vertical = 14.dp, horizontal = 16.dp),
            value = textState,
            onValueChange = { newText -> onTextChange(newText) },
            visualTransformation = visualTransformation,
            singleLine = true,
            textStyle = SSBTypography.bodySmall.copy(color = Color.Black),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        if (textState.isEmpty()) {
                            Text(
                                text = placeHolder,
                                color = SSBColor.gray400,
                                style = SSBTypography.bodySmall
                            )
                        }
                        innerTextField()
                    }

                    icon()
                }
            }
        )

        Text(
            text = helperText,
            color = SSBColor.error,
            style = SSBTypography.label
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SSBTextFieldPreview() {
    val (textState, onTextChange) = remember { mutableStateOf("") }
    val (isSelected, setIsSelected) = remember { mutableStateOf(false)}

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        SSBTextField(
            textState = textState,
            label = "아이디",
            placeHolder = "아이디를 입력해주세요",
            onTextChange = onTextChange,
            icon = {}
        )

        SSBTextField(
            textState = textState,
            label = "비밀번호",
            placeHolder = "비밀번호를 입력해주세요",
            onTextChange = onTextChange,
            icon = {
                EyeIcon(
                    isSelected = isSelected,
                    modifier = Modifier.clickableSingle { setIsSelected(!isSelected)  }
                )
            },
            visualTransformation = if(isSelected) VisualTransformation.None else PasswordVisualTransformation()
        )

        SSBTextField(
            textState = textState,
            label = "비밀번호",
            placeHolder = "비밀번호를 입력해주세요",
            isError = true,
            helperText = "아이디와 비밀번호가 일치하는지 확인해주세요",
            onTextChange = onTextChange,
            icon = {}
        )
    }
}