package com.sweat.profile.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sweat.design_system.icon.SendIcon
import com.sweat.design_system.theme.SSBTypography
import com.sweat.design_system.theme.color.SSBColor

@Composable
fun ChattingTextField(
    modifier: Modifier = Modifier,
    textState: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onclick: () -> Unit,
    updateTextValue: (String) -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(9.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.End),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(1f)
                .background(color = SSBColor.gray50, shape = RoundedCornerShape(size = 15.dp))
                .padding(horizontal = 16.dp, vertical = 7.dp)
        ) {
            BasicTextField(
                onValueChange = { newText -> updateTextValue(newText) },
                keyboardOptions = keyboardOptions,
                value = textState,
                textStyle = SSBTypography.bodySmall.copy(fontWeight = FontWeight.W400),
                modifier = Modifier.fillMaxWidth()
            )
        }
        SendIcon(
            modifier = Modifier
                .size(32.dp)
                .padding(0.dp)
                .clickable { onclick() },
        )
    }
}


@Preview
@Composable
fun ChattingTextFieldPreview() {
    ChattingTextField(
        textState = "가나다",
        updateTextValue = { _ -> },
        onclick = {}
    )
}
