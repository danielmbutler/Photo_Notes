package com.dbtechprojects.photonotes.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Color.White,
    background = Color.White,
    onPrimary = Color.Black
)

val noteBGYellow = Color(0xFFfff69b)
val noteBGBlue = Color(0xFFa1c8e9)


private val LightColorPalette = lightColors(
    primary = Color.White,
    background = Color.White,
    onPrimary = Color.Black,
)

@Composable
fun PhotoNotesTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}