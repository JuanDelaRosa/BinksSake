package com.quetzapps.akibaroom.compose

import akibaroom.core.ui.compose.BottomNavItem
import akibaroom.core.ui.compose.CustomBottomNavBar
import akibaroom.core.ui.compose.CustomTopBar
import akibaroom.core.ui.navigation.navigateSafe
import akibaroom.feature.auth.api.AuthApi
import akibaroom.feature.collection.api.CollectionApi
import akibaroom.feature.figures.api.FiguresApi
import akibaroom.feature.profile.api.ProfileApi
import akibaroom.feature.social.api.SocialApi
import akibaroom.feature.store.api.StoreApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectorRoot(
    figuresApi: FiguresApi,
    authApi: AuthApi,
    collectionApi: CollectionApi,
    profileApi: ProfileApi,
    socialApi: SocialApi,
    storeApi: StoreApi
) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val showBars = currentRoute !in setOf("profile", "add")

    Scaffold { innerPadding ->
        Box(modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(innerPadding)
        ) {
            Column {
                if (showBars) {
                    val currentTop = when (currentRoute) {
                        "discover" -> "Discover"
                        "wishlist" -> "Wishlist"
                        "favorite" -> "Favorite"
                        "store" -> "Store"
                        else -> "Figy"
                    }
                    Column(modifier = Modifier.fillMaxWidth()) {
                        CustomTopBar(
                            title = currentTop,
                            onIconClick = { navController.navigateSafe("profile") }
                        )
                    }
                }
                NavHost(navController = navController, startDestination = "discover") {
                    composable("discover") { figuresApi.Content() }
                    composable("wishlist") { authApi.Content() }
                    composable("favorite") { socialApi.Content() }
                    composable("store") { storeApi.Content() }
                    composable("profile") { profileApi.Content() }
                    composable(
                        route = "figure/{id}",
                        deepLinks = listOf(
                            navDeepLink { uriPattern = "app://akibaroom/figure/{id}" }
                        )
                    ) { /* detail screen placeholder */ }
                }
            }
            if (showBars) {
                Column(modifier = Modifier.align(Alignment.BottomCenter)) {
                    CustomBottomNavBar(
                        showSearch = true,
                        items = listOf(
                            BottomNavItem(
                                label = "Discover",
                                icon = Icons.Default.Place,
                                isSelected = currentRoute == "discover",
                                onClick = { navController.navigateSafe("discover") }
                            ),
                            BottomNavItem(
                                label = "Social",
                                icon = Icons.Default.Person,
                                isSelected = currentRoute == "favorite",
                                onClick = { navController.navigateSafe("favorite") }
                            ),
                            BottomNavItem(
                                label = "Collection",
                                icon = Icons.Default.Home,
                                isSelected = currentRoute == "wishlist",
                                onClick = { navController.navigateSafe("wishlist") }
                            ),
                        ),
                        onSearch = { navController.navigateSafe("store") }
                    )
                }
            }
        }
    }
}
