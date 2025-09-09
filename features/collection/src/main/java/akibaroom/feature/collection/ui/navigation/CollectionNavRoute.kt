package akibaroom.feature.collection.ui.navigation

import akibaroom.core.ui.compose.NavRoute
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument

object Discover : NavRoute {
    override val route = "discover"
}

object Collection : NavRoute {
    override val route = "collection"
}

object WishList : NavRoute {
    override val route = "wishlist"
}

object Search : NavRoute {
    override val route = "search"
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