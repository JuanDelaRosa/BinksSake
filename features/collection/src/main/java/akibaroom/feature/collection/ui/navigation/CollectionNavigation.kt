package akibaroom.feature.collection.ui.navigation

import akibaroom.core.ui.compose.CollectEffects
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
import akibaroom.feature.collection.collection.ui.compose.CollectionScreen
import akibaroom.feature.collection.discover.ui.compose.DiscoverScreen
import akibaroom.feature.collection.discover.ui.viewmodel.DiscoverViewModel
import akibaroom.feature.collection.collection.ui.viewmodel.CollectionViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun CollectionNavigation(
    startDestination: NavRoute = Discover
) {
    val navController = rememberNavController()
    val activity = LocalContext.current.requireActivity()
    FeatureNavigation(
        navController = navController,
        startDestination = startDestination.route,
    ) {
        composable(Discover.route) {
            val viewModel: DiscoverViewModel = hiltViewModel()
            CollectEffects(viewModel.effects) { effect ->
                when (effect) {
                    DiscoverViewModel.ViewEffect.NavigateBack -> {
                        navController.navigateUpOrFinish(activity)
                    }
                    is DiscoverViewModel.ViewEffect.OpenFigureDetails -> TODO()
                }
            }
            DiscoverScreen(
                state = viewModel.state.collectAsStateWithLifecycle().value,
                executeAction = viewModel::executeAction
            )
        }
        composable(Collection.route) {
            val viewModel: CollectionViewModel = hiltViewModel()
            CollectEffects(viewModel.effects) { effect ->
                when (effect) {
                    CollectionViewModel.ViewEffect.NavigateBack -> {
                        navController.navigateUpOrFinish(activity)
                    }
                }
            }
            CollectionScreen(
                state = viewModel.state.collectAsState().value,
                executeAction = viewModel::executeAction
            )
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
