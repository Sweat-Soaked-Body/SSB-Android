package com.sweat.design_system.component.button

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sweat.design_system.theme.SSBTypography
import com.sweat.design_system.theme.color.SSBColor

@Composable
fun SSBButton(
    modifier: Modifier = Modifier,
    text: String,
    state: ButtonState = ButtonState.Enabled,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Button(
        modifier = modifier,
        interactionSource = interactionSource,
        enabled = state.isEnabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = SSBColor.main,
            contentColor = Color.White,
            disabledContainerColor = SSBColor.main.copy(alpha = 0.5f),
            disabledContentColor = Color.White
        ),
        contentPadding = PaddingValues(vertical = 17.dp),
        shape = RoundedCornerShape(12.dp),
        onClick = onClick
    ) {
        Text(
            text = text,
            style = SSBTypography.bodyMedium
        )
    }
}

@Preview
@Composable
fun SSBButtonPreview() {
    Column(
        modifier = Modifier.height(300.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        SSBButton(
            text = "버튼",
            modifier = Modifier.fillMaxWidth()
        ) {}
        SSBButton(
            text = "버튼",
            modifier = Modifier.fillMaxWidth(),
            state = ButtonState.Disabled
        ) {}

    }
}