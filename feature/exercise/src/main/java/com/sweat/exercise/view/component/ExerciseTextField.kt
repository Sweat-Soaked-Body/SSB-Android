package com.sweat.exercise.view.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.sweat.design_system.theme.SSBAndroidTheme

@Composable
fun ExerciseTextField(
    modifier: Modifier,
    text: String,
    textState: String,
    placeHolder: String,
    onTextChange: (String) -> Unit,
){
    SSBAndroidTheme { colors, typography ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 18.dp)
        ) {
            Text(
                text = text,
                style = typography.bodySmall,
                color = colors.black
            )
            Row(
                modifier = modifier
                    .border(
                        width = 1.dp,
                        color = colors.gray100,
                        shape = RoundedCornerShape(size = 8.dp)
                    )
                    .fillMaxWidth()
                    .height(54.dp)
                    .padding(start = 16.dp, top = 14.dp, end = 149.dp, bottom = 14.dp),
                horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically,
            ) {
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
                                    text = placeHolder,
                                    color = colors.gray400,
                                    style = typography.bodySmall,
                                )
                            }
                            innerTextField()
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExerciseTextFieldPreview(){
    val (textState, onTextChange) = remember { mutableStateOf("") }

    ExerciseTextField(
        modifier = Modifier,
        text = "운동 이름",
        textState = textState,
        placeHolder = "운동 이름을 적어주세요",
        onTextChange = onTextChange,
    )
}