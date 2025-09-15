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
import androidx.compose.material.icons.filled.Place
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
    val showBars = currentRoute !in setOf(Profile.route, Search.route, "figure/{id}")

    Scaffold { innerPadding ->
        Box(modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(innerPadding)
        ) {
            Column {
                if (showBars) {
                    val currentTop = when (currentRoute) {
                        Discover.route -> "Discover"
                        Collection.route -> "Collection"
                        Social.route -> "Social"
                        Search.route -> "Search"
                        Profile.route -> "Profile"
                        else -> "Figy"
                    }
                    Column(modifier = Modifier.fillMaxWidth()) {
                        CustomTopBar(
                            title = currentTop,
                            onIconClick = { navController.navigateSafe(Profile.route) }
                        )
                    }
                }
                NavHost(navController = navController, startDestination = "discover") {
                    composable(
                        route = Discover.route,
                        enterTransition = {
                            fadeIn(animationSpec = tween(ANIMATION_DURATION))
                        },
                        exitTransition = {
                            fadeOut(animationSpec = tween(ANIMATION_DURATION))
                        }
                    ) { collectionApi.ContentDiscover() }
                    composable(
                        route = Social.route,
                        enterTransition = {
                            fadeIn(animationSpec = tween(ANIMATION_DURATION))
                        },
                        exitTransition = {
                            fadeOut(animationSpec = tween(ANIMATION_DURATION))
                        }
                    ) { socialApi.Content() }
                    composable(
                        route = Collection.route,
                        enterTransition = {
                            fadeIn(animationSpec = tween(ANIMATION_DURATION))
                        },
                        exitTransition = {
                            fadeOut(animationSpec = tween(ANIMATION_DURATION))
                        }
                    ) { collectionApi.ContentCollection() }
                    composable(Profile.route) { profileApi.Content() }
                    composable(Search.route) { collectionApi.ContentSearch() }
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
                                icon = Icons.Default.Search,
                                isSelected = currentRoute == Discover.route,
                                onClick = { navController.navigateSafe(Discover.route) }
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
                                isSelected = currentRoute == Collection.route,
                                onClick = { navController.navigateSafe(Collection.route) }
                            ),
                        ),
                        onSearch = { navController.navigateSafe(Search.route) }
                    )
                }
            }
        }
    }
}

private const val ANIMATION_DURATION = 300
