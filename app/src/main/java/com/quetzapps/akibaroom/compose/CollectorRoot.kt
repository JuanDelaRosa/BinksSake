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
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
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
    val showBars = currentRoute !in setOf(NavProfile.route, collectionApi.searchRoute, "detail/{uuid}")

    Scaffold(contentWindowInsets = WindowInsets.navigationBars
        .only(WindowInsetsSides.Top)
        .exclude(WindowInsets.statusBars)) { innerPadding ->
        Box(modifier = Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(innerPadding)
        ) {
            Column {
                NavHost(
                    navController = navController,
                    startDestination = collectionApi.discoverRoute,
                    enterTransition = {
                        slideInHorizontally(
                            animationSpec = tween(300),
                            initialOffsetX = { it })
                    },
                    exitTransition = {
                        slideOutHorizontally(
                            animationSpec = tween(300),
                            targetOffsetX = { -it })
                    },
                    popEnterTransition = {
                        slideInHorizontally(
                            animationSpec = tween(300),
                            initialOffsetX = { -it })
                    },
                    popExitTransition = {
                        slideOutHorizontally(
                            animationSpec = tween(300),
                            targetOffsetX = { it })
                    }
                ) {
                    collectionApi.registerGraph(navController, this)
                    composable(NavProfile.route) { profileApi.Content() }
                    composable(NavSocial.route) { socialApi.Content() }
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
                            isSelected = currentRoute == NavSocial.route,
                            onClick = { navController.navigateSafe(NavSocial.route) }
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
