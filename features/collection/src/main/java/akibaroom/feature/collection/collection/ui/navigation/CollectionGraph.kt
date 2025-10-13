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
            }
        }
        CollectionScreen(
            state = viewModel.state.collectAsState().value,
            executeAction = viewModel::executeAction
        )
    }
}

object NavCollection : NavRoute {
    override val route = "collection"
}
