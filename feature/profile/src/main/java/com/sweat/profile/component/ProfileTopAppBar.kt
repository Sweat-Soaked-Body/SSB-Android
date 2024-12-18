package com.sweat.profile.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sweat.design_system.theme.SSBTypography

@Composable
fun ProfileTopAppBar(
    modifier: Modifier = Modifier,
    startText: String,
    endIcon: @Composable () -> Unit
) {
    Row(
        modifier = modifier.padding(
            vertical = 13.dp,
            horizontal = 24.dp
        ),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = startText,
            style = SSBTypography.titleSmall,
            fontWeight = FontWeight(600),
            color = Color(0xFF000000),
        )
        endIcon()
    }
}

