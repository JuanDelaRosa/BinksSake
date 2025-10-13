package com.quetzapps.akibaroom

import akibaroom.core.datastore.ThemePreference
import akibaroom.core.datastore.ThemePreferencesRepository
import akibaroom.core.ui.theme.CollectorTheme
import akibaroom.core.utils.enviroment.AppContextProvider
import akibaroom.feature.auth.api.AuthApi
import akibaroom.feature.collection.api.CollectionApi
import akibaroom.feature.figures.api.FiguresApi
import akibaroom.feature.profile.api.ProfileApi
import akibaroom.feature.social.api.SocialApi
import akibaroom.feature.store.api.StoreApi
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.quetzapps.akibaroom.compose.CollectorRoot
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var themePrefs: ThemePreferencesRepository
    @Inject lateinit var authApi: AuthApi
    @Inject lateinit var collectionApi: CollectionApi
    @Inject lateinit var profileApi: ProfileApi
    @Inject lateinit var socialApi: SocialApi
    @Inject lateinit var storeApi: StoreApi
    @Inject lateinit var figuresApi: FiguresApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppContextProvider.activity = this
        setContent {
            val theme by themePrefs.theme.collectAsState(initial = ThemePreference.SYSTEM)
            val useDark = when (theme) {
                ThemePreference.SYSTEM -> androidx.compose.foundation.isSystemInDarkTheme()
                ThemePreference.LIGHT -> false
                ThemePreference.DARK -> true
            }
            CollectorTheme(useDarkTheme = useDark) {
                val view = LocalView.current
                SideEffect {
                    val window = (view.context as ComponentActivity).window
                    WindowCompat.setDecorFitsSystemWindows(window, false)
                    window.statusBarColor = android.graphics.Color.TRANSPARENT
                    window.navigationBarColor = android.graphics.Color.TRANSPARENT
                    WindowInsetsControllerCompat(window, window.decorView).apply {
                        isAppearanceLightStatusBars = !useDark
                        isAppearanceLightNavigationBars = !useDark
                    }
                }
                CollectorRoot(
                    authApi = authApi,
                    collectionApi = collectionApi,
                    profileApi = profileApi,
                    socialApi = socialApi,
                    storeApi = storeApi,
                    figuresApi = figuresApi
                )
            }
        }
    }
}
