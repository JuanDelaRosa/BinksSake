package binkssake.feature.stores.ui.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import binkssake.core.ui.compose.NavRoute

object ListOfStores : NavRoute {
    override val route = "list"
}

object StoreDetail : NavRoute {
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
