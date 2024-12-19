package com.sweat.exercise.view.component

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import com.sweat.design_system.component.button.ButtonState
import com.sweat.design_system.theme.SSBTypography
import com.sweat.design_system.theme.color.SSBColor

@Composable
fun ExerciseButton(
    modifier: Modifier,
    text: String,
    state: ButtonState = ButtonState.Enabled,
    onClick: () -> Unit
){
    val interactionSource = remember { MutableInteractionSource() }

    Button(
        modifier = modifier
            .border(
                width = 1.dp,
                color = if (state == ButtonState.Enabled) SSBColor.gray100 else SSBColor.black,
                shape = RoundedCornerShape(size = 12.dp)
            )
            .width(48.dp)
            .height(24.dp),
        interactionSource = interactionSource,
        enabled = state.isEnabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black,
            disabledContainerColor = Color.Black,
            disabledContentColor = Color.White
        ),
        shape = RoundedCornerShape(size = 12.dp),
        contentPadding = PaddingValues(0.dp),
        onClick = onClick
    ) {
        Text(
            text = text,
            style = SSBTypography.label
        )
    }
}

@Preview
@Composable
fun ExerciseButtonPreview() {
    Column(
        modifier = Modifier.height(300.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        ExerciseButton(
            text = "버튼",
            modifier = Modifier.fillMaxWidth(),
            state = ButtonState.Enabled
        ) {}
        ExerciseButton(
            text = "버튼",
            modifier = Modifier.fillMaxWidth(),
            state = ButtonState.Disabled
        ) {}
    }
}