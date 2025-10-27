package akibaroom.feature.collection.collection.ui.navigation

import akibaroom.core.ui.compose.CollectEffects
import akibaroom.core.ui.compose.NavRoute
import akibaroom.core.ui.navigation.navigateUpOrFinish
import akibaroom.feature.collection.collection.ui.compose.CollectionScreen
import akibaroom.feature.collection.collection.ui.viewmodel.CollectionViewModel
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.collectionGraph(navController: NavController) {
    composable(NavCollection.route) {
        val viewModel: CollectionViewModel = hiltViewModel()
        CollectEffects(viewModel.effects) { effect ->
            when (effect) {
                CollectionViewModel.ViewEffect.NavigateBack -> navController.navigateUpOrFinish()
                is CollectionViewModel.ViewEffect.NavigateToDetail -> {
                    navController.navigate("figure_detail/${effect.figureId}")
                }

                is CollectionViewModel.ViewEffect.NavigateToStats -> {
                    navController.navigate("collection_stats")
                }

                is CollectionViewModel.ViewEffect.ShareCollection -> {
                }
            }
        }
        CollectionScreen(
            state = viewModel.state.collectAsState().value,
            figuresPaging = viewModel.figuresPaging,
            executeAction = viewModel::executeAction
        )
    }
}

object NavCollection : NavRoute {
    override val route = "collection"
}
