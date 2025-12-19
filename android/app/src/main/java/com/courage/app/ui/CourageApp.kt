package com.courage.app.ui

import androidx.compose.runtime.Composable
import com.courage.app.ui.navigation.AppNavHost
import com.courage.app.ui.theme.CourageTheme

@Composable
fun CourageApp() {
    CourageTheme {
        AppNavHost()
    }
}


