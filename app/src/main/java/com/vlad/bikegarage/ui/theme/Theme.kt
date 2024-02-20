package com.vlad.bikegarage.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val darkColorPalette = darkColors(
    primary = Blue,
    secondary = GrayBlue,
    secondaryVariant = OceanBlue,
    background = Black,
    surface = DarkBlue,
    onSecondary = Gray,
    onBackground = White,
    onPrimary = White,

)

private val lightColorPalette = lightColors(
    primary = Blue,
    primaryVariant = Blue,
    secondary = Blue

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun BikeGarageTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        darkColorPalette
    } else {
        darkColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}