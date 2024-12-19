package com.sweat.exercise.view.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.sweat.design_system.theme.SSBAndroidTheme

@Composable
fun ExerciseTextField(
    modifier: Modifier = Modifier,
    text: String,
    textState: String,
    onTextChange: (String) -> Unit,
) {
    SSBAndroidTheme { colors, typography ->
        BasicTextField(
            modifier = modifier.fillMaxWidth(),
            value = textState,
            onValueChange = { newText -> onTextChange(newText) },
            textStyle = typography.bodySmall.copy(textAlign = TextAlign.Start),
            singleLine = true,
            decorationBox = { innerTextField ->
                Box(
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (textState.isEmpty()) {
                        Text(
                            text = text,
                            color = colors.gray200,
                            style = typography.bodySmall,
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ExerciseTextFieldPreview() {
    val (textState, onTextChange) = remember { mutableStateOf("") }

    ExerciseTextField(
        modifier = Modifier,
        text = "운동을 적어주세요",
        textState = textState,
        onTextChange = onTextChange
    )
}