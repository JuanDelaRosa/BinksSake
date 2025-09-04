package com.quetzapps.akibaroom.compose

import akibaroom.feature.figures.api.FiguresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectorRoot(figuresApi: FiguresApi) {
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
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            NavHost(navController = navController, startDestination = "collection") {
                composable("collection") { figuresApi.Content() }
                composable("profile") { ProfileFlowScreen(onClose = { navController.popBackStack() }) }
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
private fun ProfileFlowScreen(onClose: () -> Unit) {
    Text(text = "Profile flow placeholder")
}
