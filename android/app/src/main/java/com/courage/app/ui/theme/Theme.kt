package com.courage.app.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColors = lightColorScheme(
    primary = NeutralColors.Accent,
    onPrimary = NeutralColors.OnAccent,
    primaryContainer = NeutralColors.PrimaryContainer,
    onPrimaryContainer = NeutralColors.OnPrimaryContainer,
    secondary = NeutralColors.Secondary,
    secondaryContainer = NeutralColors.SecondaryContainer,
    onSecondaryContainer = NeutralColors.OnSecondaryContainer,
    background = NeutralColors.Surface,
    onBackground = NeutralColors.OnSurface,
    surface = NeutralColors.Surface,
    onSurface = NeutralColors.OnSurface,
    surfaceVariant = NeutralColors.SurfaceVariant,
    onSurfaceVariant = NeutralColors.OnSurfaceVariant,
    outline = NeutralColors.Outline,
)

private val DarkColors = darkColorScheme(
    primary = NeutralColors.AccentDark,
    onPrimary = NeutralColors.OnAccentDark,
    primaryContainer = NeutralColors.PrimaryContainerDark,
    onPrimaryContainer = NeutralColors.OnPrimaryContainerDark,
    secondary = NeutralColors.SecondaryDark,
    secondaryContainer = NeutralColors.SecondaryContainerDark,
    onSecondaryContainer = NeutralColors.OnSecondaryContainerDark,
    background = NeutralColors.SurfaceDark,
    onBackground = NeutralColors.OnSurfaceDark,
    surface = NeutralColors.SurfaceDark,
    onSurface = NeutralColors.OnSurfaceDark,
    surfaceVariant = NeutralColors.SurfaceVariantDark,
    onSurfaceVariant = NeutralColors.OnSurfaceVariantDark,
    outline = NeutralColors.OutlineDark,
)

@Composable
fun CourageTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S ->
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        darkTheme -> DarkColors
        else -> LightColors
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content,
    )
}


