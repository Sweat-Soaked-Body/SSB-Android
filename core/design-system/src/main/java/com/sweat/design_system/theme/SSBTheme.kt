package com.sweat.design_system.theme

import androidx.compose.runtime.Composable
import com.sweat.design_system.theme.color.ColorTheme
import com.sweat.design_system.theme.color.SSBColor

@Composable
fun SSBAndroidTheme(
    colors: ColorTheme = SSBColor,
    typography: SSBTypography,
    content: @Composable (colors: ColorTheme, typography: SSBTypography) -> Unit
){
    content(colors, typography)
}