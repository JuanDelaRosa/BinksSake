package akibaroom.feature.collection.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import akibaroom.core.ui.compose.FeatureNavigation
import akibaroom.core.ui.compose.NavRoute
import akibaroom.core.ui.navigation.navigateUpOrFinish
import akibaroom.core.utils.extentions.requireActivity
import akibaroom.feature.collection.ui.CollectionViewModel

@Composable
internal fun CollectionNavigation(
    startDestination: NavRoute = Discover,
    viewModel: CollectionViewModel
) {
    val navController = rememberNavController()
    val state = viewModel.state.collectAsState().value
    val activity = LocalContext.current.requireActivity()

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                CollectionViewModel.ViewEffect.NavigateBack -> {
                    navController.navigateUpOrFinish(activity)
                }
            }
        }
    }

    FeatureNavigation(
        navController = navController,
        startDestination = startDestination.route,
    ) {
        composable(Discover.route) {
            // Placeholder Collection Screen
            Text("Discover Screen - Coming Soon")
        }
        composable(Collection.route) {
            // Placeholder Collection Screen
            Text("Collection Screen - Coming Soon")
        }
        composable(WishList.route) {
            // Placeholder Collection Screen
            Text("WishList Screen - Coming Soon")
        }
        composable(Search.route) {
            // Placeholder Collection Screen
            Text("Search Screen - Coming Soon")
        }
    }
}