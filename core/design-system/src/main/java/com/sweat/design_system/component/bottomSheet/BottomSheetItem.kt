package com.sweat.design_system.component.bottomSheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sweat.design_system.theme.SSBTypography

@Composable
fun BottomSheetItem(
    icon: @Composable () -> Unit,
    title: String,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        icon()
        Text(
            text = title,
            style = SSBTypography.bodySmall,
            fontWeight = FontWeight(400),
            color = textColor
        )
    }
}
