package akibaroom.feature.collection.detail.ui.navigation

import akibaroom.core.ui.compose.CollectEffects
import akibaroom.core.ui.compose.NavRoute
import akibaroom.core.ui.navigation.navigateUpOrFinish
import akibaroom.feature.collection.detail.ui.compose.FigureDetailScreen
import akibaroom.feature.collection.detail.ui.viewmodel.FigureDetailViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

fun NavGraphBuilder.figureDetailGraph(navController: NavController) {
    composable(
        route = NavFigureDetail.route,
        arguments = NavFigureDetail.arguments()
    ) { backStackEntry ->
        val viewModel: FigureDetailViewModel = hiltViewModel(backStackEntry)
        CollectEffects(viewModel.effects) { effect ->
            when (effect) {
                is FigureDetailViewModel.ViewEffect.NavigateBack -> {
                    navController.navigateUpOrFinish()
                }
            }
        }
        FigureDetailScreen(
            state = viewModel.state.collectAsStateWithLifecycle().value,
            executeAction = viewModel::executeAction
        )
    }
}

object NavFigureDetail : NavRoute {
    const val ARG_UUID = "uuid"
    private const val BASE_ROUTE = "detail"

    override val route = "$BASE_ROUTE/{$ARG_UUID}"

    override fun arguments() = listOf(
        navArgument(ARG_UUID) {
            type = NavType.StringType
        }
    )

    override fun build(vararg args: Any?): String {
        val uuid = args.getOrNull(0) ?: throw IllegalArgumentException("Uuid is required")
        return "$BASE_ROUTE/$uuid"
    }
}
