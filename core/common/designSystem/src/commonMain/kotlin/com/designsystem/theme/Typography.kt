package com.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import org.jetbrains.compose.resources.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp
import raya_challenge.core.common.designsystem.generated.resources.Res
import raya_challenge.core.common.designsystem.generated.resources.roboto_bold
import raya_challenge.core.common.designsystem.generated.resources.roboto_semi_bold
import raya_challenge.core.common.designsystem.generated.resources.roboto_medium
import raya_challenge.core.common.designsystem.generated.resources.roboto_regular
import raya_challenge.core.common.designsystem.generated.resources.roboto_light

@Composable
fun Typography(): Typography {
    val roboto = FontFamily(
        Font(Res.font.roboto_bold, FontWeight.Bold),
        Font(Res.font.roboto_semi_bold, FontWeight.SemiBold),
        Font(Res.font.roboto_medium, FontWeight.Medium),
        Font(Res.font.roboto_regular, FontWeight.Normal),
        Font(Res.font.roboto_light, FontWeight.Light)
    )

    return Typography(
        titleLarge = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.Both,
            )
        ),
        titleMedium = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.Both,
            )
        ),
        bodyLarge = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.Both,
            )
        ),
        bodyMedium = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.Both,
            )
        ),
        bodySmall = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Light,
            fontSize = 12.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.Both,
            )
        ),
        displaySmall = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.Both,
            )
        )
    )
}