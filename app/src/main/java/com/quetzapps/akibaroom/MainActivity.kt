package com.quetzapps.akibaroom

import akibaroom.core.datastore.ThemePreference
import akibaroom.core.datastore.ThemePreferencesRepository
import akibaroom.core.ui.theme.CollectorTheme
import akibaroom.feature.figures.api.FiguresApi
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.quetzapps.akibaroom.compose.CollectorRoot
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var themePrefs: ThemePreferencesRepository
    @Inject lateinit var figuresApi: FiguresApi

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
                CollectorRoot(figuresApi = figuresApi)
            }
        }
    }
}
