package com.school_of_company.main.view.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sweat.design_system.component.modifier.clickableSingle
import com.sweat.design_system.theme.SSBAndroidTheme

@Composable
fun MainTabRowItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    title: String,
    onClick: () -> Unit
) {
    SSBAndroidTheme { colors, typography ->
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .clickableSingle { onClick() }
                .padding(bottom = 7.dp)
        ) {
            Text(
                text = title,
                style = typography.bodyMedium,
                color = if (selected) colors.black else colors.gray400
            )
        }
    }
}