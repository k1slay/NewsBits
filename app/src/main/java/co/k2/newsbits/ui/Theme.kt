package co.k2.newsbits.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightThemeColors = lightColors(
    primary = Color(0xFFcbf3f0),
    primaryVariant = Color(0xFFcbf3f0),
    onPrimary = Color.Black,
    secondary = Color(0xFF6200EE),
    secondaryVariant = Color(0xFF3700B3),
    onSurface = Color.DarkGray,
    onSecondary = Color.Gray,
    error = Color.Red
)

private val DarkThemeColors = darkColors(
    primary = Color(0xFF2ec4b6),
    primaryVariant = Color(0xFF2ec4b6),
    onPrimary = Color.White,
    secondary = Color(0xFF6200EE),
    secondaryVariant = Color(0xFF3700B3),
    onSurface = Color.LightGray,
    onSecondary = Color.Gray,
    error = Color.Red,
    background = Color(0xFF191919)
)

@Composable
fun NewslyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) DarkThemeColors else LightThemeColors,
        typography = NewslyTypography,
        content = content
    )
}