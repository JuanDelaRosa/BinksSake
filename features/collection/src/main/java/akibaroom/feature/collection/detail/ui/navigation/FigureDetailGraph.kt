package akibaroom.feature.collection.detail.ui.navigation

import akibaroom.core.ui.compose.NavRoute
import android.app.Activity
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

fun NavGraphBuilder.figureDetailGraph(navController: NavController) {
    composable(
        route = FigureDetail.route,
        arguments = FigureDetail.arguments()
    ) { backStackEntry ->
        val index = FigureDetail.extractIndex(backStackEntry)
        //val figure = paging.itemSnapshotList.getOrNull(index)
        /*if (figure == null) {
            navController.popBackStack()
        } else {
            FigureDetailScreen(
                figure = figure,
                executeAction = viewModel::executeAction
            )
        }*/
    }
}

object FigureDetail : NavRoute {
    private const val ARG_INDEX = "index"
    private const val BASE_ROUTE = "detail"

    override val route = "$BASE_ROUTE/{$ARG_INDEX}"

    override fun arguments() = listOf(
        navArgument(ARG_INDEX) {
            type = NavType.IntType
        }
    )

    override fun build(vararg args: Any?): String {
        val index = args.getOrNull(0) ?: throw IllegalArgumentException("Index is required")
        return "$BASE_ROUTE/$index"
    }

    fun extractIndex(backStackEntry: NavBackStackEntry): Int {
        return backStackEntry.arguments?.getInt(ARG_INDEX) ?: -1
    }
}
