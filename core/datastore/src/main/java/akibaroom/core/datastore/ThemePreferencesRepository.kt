package akibaroom.core.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

enum class ThemePreference { SYSTEM, LIGHT, DARK }

private val Context.themeDataStore by preferencesDataStore(name = "theme_prefs")

@Singleton
class ThemePreferencesRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val THEME_KEY = intPreferencesKey("theme_mode")

    val theme: Flow<ThemePreference> = context.themeDataStore.data.map { prefs ->
        when (prefs[THEME_KEY] ?: 0) {
            1 -> ThemePreference.LIGHT
            2 -> ThemePreference.DARK
            else -> ThemePreference.SYSTEM
        }
    }

    suspend fun setTheme(themePreference: ThemePreference) {
        context.themeDataStore.edit { prefs: Preferences ->
            val value = when (themePreference) {
                ThemePreference.SYSTEM -> 0
                ThemePreference.LIGHT -> 1
                ThemePreference.DARK -> 2
            }
            prefs[THEME_KEY] = value
        }
    }
}


