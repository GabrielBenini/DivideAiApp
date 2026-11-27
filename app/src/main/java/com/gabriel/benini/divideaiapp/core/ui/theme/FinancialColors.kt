package com.gabriel.benini.divideaiapp.core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class FinancialColors(
    val positive: Color,
    val negative: Color,
    val neutral: Color
)

val LightFinancialColors = FinancialColors(
    positive = LightPositive,
    negative = LightNegative,
    neutral = LightNeutral
)

val DarkFinancialColors = FinancialColors(
    positive = DarkPositive,
    negative = DarkNegative,
    neutral = DarkNeutral
)

val LocalFinancialColors = staticCompositionLocalOf<FinancialColors> {
    LightFinancialColors
}

val MaterialTheme.financialColors: FinancialColors
    @Composable
    @ReadOnlyComposable
    get() = LocalFinancialColors.current