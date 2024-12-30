package com.school_of_company.main.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
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
import com.sweat.design_system.theme.SSBTypography
import com.sweat.design_system.theme.color.SSBColor

@Composable
fun ExerciseTextField(
    modifier: Modifier = Modifier,
    textState: String,
    onTextChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    SSBAndroidTheme { colors, typography ->

        // 상태 관리 부분
        val currentText = remember { mutableStateOf(textState) }

        BasicTextField(
            modifier = modifier
                .width(60.dp)
                .background(color = colors.gray100, shape = RoundedCornerShape(size = 8.dp))
                .padding(start = 19.dp, top = 1.dp, end = 11.dp, bottom = 1.dp),
            value = currentText.value,
            onValueChange = { newText ->
                if(currentText.value.length > 2) {
                    currentText.value = newText.take(3)
                }
                else {
                    currentText.value = newText
                }
                onTextChange(newText)
            },
            textStyle = SSBTypography.bodySmall.copy(textAlign = TextAlign.End),
            decorationBox = { innerTextField ->
                Box(
                    contentAlignment = Alignment.CenterEnd
                ) {
                    innerTextField()
                }
            },
            keyboardOptions = keyboardOptions
        )
    }
}
