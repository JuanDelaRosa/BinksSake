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
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    val showBars = currentRoute !in setOf(Profile.route, collectionApi.searchRoute, "detail/{uuid}")

    Scaffold { innerPadding ->
        Box(modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(innerPadding)
        ) {
            Column {
                if (showBars) {
                    val currentTop = when (currentRoute) {
                        collectionApi.discoverRoute -> "Discover"
                        collectionApi.collectionRoute -> "Collection"
                        Social.route -> "Social"
                        collectionApi.searchRoute -> "Search"
                        Profile.route -> "Profile"
                        else -> "Figy"
                    }
                    CustomTopBar(
                        title = currentTop,
                        onIconClick = { navController.navigateSafe(Profile.route) }
                    )
                }
                NavHost(navController = navController, startDestination = collectionApi.discoverRoute) {
                    collectionApi.registerGraph(navController, this)
                    composable(Profile.route) { profileApi.Content() }
                    composable(Social.route) { socialApi.Content() }
                }
            }
            if (showBars) {
                CustomBottomNavBar(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    showSearch = true,
                    items = listOf(
                        BottomNavItem(
                            label = "Discover",
                            icon = Icons.Default.Search,
                            isSelected = currentRoute == collectionApi.discoverRoute,
                            onClick = { navController.navigateSafe(collectionApi.discoverRoute) }
                        ),
                        BottomNavItem(
                            label = "Social",
                            icon = Icons.Default.Person,
                            isSelected = currentRoute == Social.route,
                            onClick = { navController.navigateSafe(Social.route) }
                        ),
                        BottomNavItem(
                            label = "Collection",
                            icon = Icons.Default.Home,
                            isSelected = currentRoute == collectionApi.collectionRoute,
                            onClick = { navController.navigateSafe(collectionApi.collectionRoute) }
                        ),
                    ),
                    onSearch = { navController.navigateSafe(collectionApi.searchRoute) }
                )
            }
        }
    }
}
