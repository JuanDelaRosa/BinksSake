package com.quetzapps.akibaroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import akibaroom.core.ui.theme.CollectorTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import akibaroom.core.datastore.ThemePreference
import akibaroom.core.datastore.ThemePreferencesRepository
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import akibaroom.feature.figures.ui.navigation.FiguresEntry
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var themePrefs: ThemePreferencesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val theme by themePrefs.theme.collectAsState(initial = ThemePreference.SYSTEM)
            val useDark = when (theme) {
                ThemePreference.SYSTEM -> androidx.compose.foundation.isSystemInDarkTheme()
                ThemePreference.LIGHT -> false
                ThemePreference.DARK -> true
            }
            CollectorTheme(useDarkTheme = useDark) {
                CollectorRoot()
            }
        }
    }
}

@Composable
fun CollectorRoot() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "figure") {
        composable("figure") { FiguresEntry() }
        composable("figure/{id}") { /* detail screen placeholder */ }
    }
}
