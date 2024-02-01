package com.cc221020.ccl3.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = Complementary80,
    secondary = Secondary80,
    tertiary = Primary80,
    onPrimary = Main80,
    onSecondary = Primary40,
    onTertiary = Main80,
    onBackground = Main60,
    background = Complementary20,
    onSurface = Complementary40
)

private val DarkColorScheme = darkColorScheme(
    primary = Complementary80,
    secondary = Secondary80,
    tertiary = Primary80,
    onPrimary = Bonus80,
    onSecondary = Primary40,
    onTertiary = Main80,
    background = Main80,
    onBackground = Complementary20,
    onSurface = Complementary40
)


@Composable
fun YouMeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {// this is supposed to automatically set the color theme to dark or light mode based on what the user has set
    //however, due to problems with this feature it is hardcoded to the lightcolortheme
    val colorScheme = LightColorScheme/*when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }*/
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}