package akibaroom.feature.figures.ui.navigation

import android.content.Intent
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import akibaroom.core.ui.compose.FeatureNavigation
import akibaroom.core.ui.navigation.navigateSafe
import akibaroom.core.ui.navigation.navigateUpOrFinish
import akibaroom.core.utils.extentions.requireActivity
import akibaroom.feature.figures.ui.compose.FigureDetailScreen
import akibaroom.feature.figures.ui.FigureViewModel
import akibaroom.feature.figures.ui.compose.FiguresScreen
import androidx.core.net.toUri
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
internal fun FiguresNavigation(
    viewModel: FigureViewModel
) {
    val navController = rememberNavController()
    val state = viewModel.state.collectAsState().value
    val paging = viewModel.pagingFlow.collectAsLazyPagingItems()
    val activity = LocalContext.current.requireActivity()

    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is FigureViewModel.ViewEffect.OpenFigureDetails -> {
                    navController.navigateSafe(FigureDetail.build(effect.index))
                }
                FigureViewModel.ViewEffect.NavigateBack -> {
                    navController.navigateUpOrFinish(activity)
                }
            }
        }
    }

    FeatureNavigation(
        navController = navController,
        startDestination = ListOfFigures.route,
    ) {
        composable(ListOfFigures.route) {
            FiguresScreen(
                pagingFlow = paging,
                state = state,
                executeAction = viewModel::executeAction
            )
        }
        composable(
            route = FigureDetail.route,
            arguments = FigureDetail.arguments()
        ) { backStackEntry ->
            val index = FigureDetail.extractIndex(backStackEntry)
            val figure = paging.itemSnapshotList.getOrNull(index)
            if (figure == null) {
                navController.popBackStack()
            } else {
                FigureDetailScreen(
                    figure = figure,
                    executeAction = viewModel::executeAction
                )
            }
        }
    }
}
