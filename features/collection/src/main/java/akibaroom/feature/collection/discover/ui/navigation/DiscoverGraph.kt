package akibaroom.feature.collection.discover.ui.navigation

import akibaroom.core.ui.compose.CollectEffects
import akibaroom.core.ui.compose.NavRoute
import akibaroom.core.ui.navigation.navigateSafe
import akibaroom.core.ui.navigation.navigateUpOrFinish
import akibaroom.feature.collection.detail.ui.navigation.FigureDetail
import akibaroom.feature.collection.discover.ui.compose.DiscoverScreen
import akibaroom.feature.collection.discover.ui.viewmodel.DiscoverViewModel
import android.app.Activity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.discoverGraph(navController: NavController) {
    composable(Discover.route) {
        val viewModel: DiscoverViewModel = hiltViewModel()
        CollectEffects(viewModel.effects) { effect ->
            when (effect) {
                DiscoverViewModel.ViewEffect.NavigateBack -> {
                    navController.navigateUpOrFinish()
                }
                is DiscoverViewModel.ViewEffect.OpenFigureDetails -> {
                    navController.navigateSafe(FigureDetail.build(effect.figureId))
                }
            }
        }
        DiscoverScreen(
            state = viewModel.state.collectAsStateWithLifecycle().value,
            executeAction = viewModel::executeAction
        )
    }
}

object Discover : NavRoute {
    override val route = "discover"
}
