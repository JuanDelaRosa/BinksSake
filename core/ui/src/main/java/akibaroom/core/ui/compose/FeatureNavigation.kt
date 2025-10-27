package akibaroom.core.ui.compose

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun FeatureNavigation(
    navController: NavHostController = rememberNavController(),
    startDestination: String,
    routes: NavGraphBuilder.() -> Unit
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {
        NavHost(
            navController = navController,
            startDestination = startDestination,
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
            routes()
        }
    }
}
