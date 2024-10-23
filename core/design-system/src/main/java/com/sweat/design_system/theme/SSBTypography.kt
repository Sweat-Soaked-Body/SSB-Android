package com.sweat.design_system.theme

import androidx.compose.runtime.Stable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sweat.design_system.R

object SSBTypography {
    private val pretendard = FontFamily(
        Font(R.font.pretendard_black, FontWeight.Black),
        Font(R.font.pretendard_bold, FontWeight.Bold),
        Font(R.font.pretendard_extrabold, FontWeight.ExtraBold),
        Font(R.font.pretendard_extralight, FontWeight.ExtraLight),
        Font(R.font.pretendard_light, FontWeight.Light),
        Font(R.font.pretendard_medium,FontWeight.Medium),
        Font(R.font.pretendard_regular, FontWeight.Normal),
        Font(R.font.pretendard_semibold, FontWeight.SemiBold),
        Font(R.font.pretendard_thin, FontWeight.Thin),
    )

    // TitleLarge
    @Stable
    val titleLarge = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.SemiBold,
        fontSize = 48.sp,
        lineHeight = 58.sp
    )

    // TitleMedium
    @Stable
    val titleMedium = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.SemiBold,
        fontSize = 36.sp,
        lineHeight = 43.sp
    )

    // TitleSmall
    @Stable
    val titleSmall = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 31.sp
    )

    // SubTitle
    @Stable
    val subTitle = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 27.sp
    )

    // BodyLarge
    @Stable
    val bodyLarge = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 27.sp
    )

    // BodyMedium
    @Stable
    val bodyMedium = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 20.sp
    )

    // BodySmall
    @Stable
    val bodySmall = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 26.sp
    )

    // Label
    @Stable
    val label = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 18.sp
    )

    // Navi
    @Stable
    val navi = TextStyle(
        fontFamily = pretendard,
        fontWeight = FontWeight.SemiBold,
        fontSize = 8.sp,
        lineHeight = 12.sp
    )
}