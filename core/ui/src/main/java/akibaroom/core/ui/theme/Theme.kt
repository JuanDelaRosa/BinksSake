package akibaroom.core.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    // Primary colors
    primary = DarkAccent,
    onPrimary = DarkBackground,
    primaryContainer = DarkSurface,
    onPrimaryContainer = DarkPrimaryText,

    // Secondary colors
    secondary = DarkSecondaryText,
    onSecondary = DarkBackground,
    secondaryContainer = DarkSurface,
    onSecondaryContainer = DarkPrimaryText,

    // Tertiary colors
    tertiary = DarkAccent,
    onTertiary = DarkBackground,
    tertiaryContainer = DarkSurface,
    onTertiaryContainer = DarkPrimaryText,

    // Surface colors
    surface = DarkSurface,
    onSurface = DarkPrimaryText,
    surfaceVariant = DarkBackground,
    onSurfaceVariant = DarkSecondaryText,
    surfaceTint = DarkAccent,

    // Background colors
    background = DarkBackground,
    onBackground = DarkPrimaryText,

    // Error colors
    error = DarkError,
    onError = DarkBackground,
    errorContainer = DarkSurface,
    onErrorContainer = DarkError,

    // Outline colors
    outline = DarkBorder,
    outlineVariant = DarkBorder
)

private val LightColorScheme = lightColorScheme(
    // Primary colors
    primary = LightAccent,
    onPrimary = LightSurface,
    primaryContainer = LightSurface,
    onPrimaryContainer = LightPrimaryText,

    // Secondary colors
    secondary = LightSecondaryText,
    onSecondary = LightSurface,
    secondaryContainer = LightSurface,
    onSecondaryContainer = LightPrimaryText,

    // Tertiary colors
    tertiary = LightAccent,
    onTertiary = LightSurface,
    tertiaryContainer = LightSurface,
    onTertiaryContainer = LightPrimaryText,

    // Surface colors
    surface = LightSurface,
    onSurface = LightPrimaryText,
    surfaceVariant = LightBackground,
    onSurfaceVariant = LightSecondaryText,
    surfaceTint = LightAccent,

    // Background colors
    background = LightBackground,
    onBackground = LightPrimaryText,

    // Error colors
    error = LightError,
    onError = LightSurface,
    errorContainer = LightSurface,
    onErrorContainer = LightError,

    // Outline colors
    outline = LightBorder,
    outlineVariant = LightBorder
)

@Composable
fun CollectorTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (useDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        useDarkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
