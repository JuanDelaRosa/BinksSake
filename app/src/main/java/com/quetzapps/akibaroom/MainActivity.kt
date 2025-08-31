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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import akibaroom.feature.figures.ui.navigation.FiguresEntry
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.navigation.navDeepLink
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectorRoot() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val showBars = currentRoute !in setOf("profile", "add")

    Scaffold(
        topBar = {
            if (showBars) {
                TopAppBar(
                    title = { Text("Juan's Room") },
                    actions = {
                        IconButton(onClick = { navController.navigate("profile") }) {
                            Icon(
                                imageVector = Icons.Filled.AccountCircle,
                                contentDescription = "Profile"
                            )
                        }
                    }
                )
            }
        },
        bottomBar = {
            if (showBars) {
                NavigationBar {
                    NavigationBarItem(
                        selected = currentRoute == "collection",
                        onClick = { if (currentRoute != "collection") navController.navigate("collection") },
                        icon = { Icon(Icons.Filled.Home, contentDescription = null) },
                        label = { Text(text = "Collection") }
                    )
                    NavigationBarItem(
                        selected = currentRoute == "wishlist",
                        onClick = { if (currentRoute != "wishlist") navController.navigate("wishlist") },
                        icon = { Icon(Icons.Filled.Star, contentDescription = null) },
                        label = { Text(text = "Wishlist") }
                    )
                    NavigationBarItem(
                        selected = currentRoute == "add",
                        onClick = { navController.navigate("add") },
                        icon = { Icon(Icons.Filled.Add, contentDescription = null) },
                        label = { Text(text = "Add") }
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = {
                            // Placeholder search action depending on current tab
                            // Could navigate to a search route if needed in the future
                        },
                        icon = { Icon(Icons.Filled.Search, contentDescription = null) },
                        label = { Text(text = "Search") }
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            NavHost(navController = navController, startDestination = "collection") {
                composable("collection") { FiguresEntry() }
                composable("wishlist") { WishlistScreen() }
                composable("profile") { ProfileFlowScreen(onClose = { navController.popBackStack() }) }
                composable("add") { AddFlowScreen(onClose = { navController.popBackStack() }) }
                composable(
                    route = "figure/{id}",
                    deepLinks = listOf(
                        navDeepLink { uriPattern = "app://akibaroom/figure/{id}" }
                    )
                ) { /* detail screen placeholder */ }
            }
        }
    }
}

@Composable
private fun WishlistScreen() {
    Text(text = "Wishlist placeholder")
}

@Composable
private fun ProfileFlowScreen(onClose: () -> Unit) {
    Text(text = "Profile flow placeholder")
}

@Composable
private fun AddFlowScreen(onClose: () -> Unit) {
    Text(text = "Add flow placeholder")
}
