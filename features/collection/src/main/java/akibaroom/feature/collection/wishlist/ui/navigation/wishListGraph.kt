package akibaroom.feature.collection.wishlist.ui.navigation

import akibaroom.core.ui.compose.NavRoute
import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.wishListGraph(navController: NavController) {
    composable(NavWishList.route) {
        // Placeholder Collection Screen
        Text("WishList Screen - Coming Soon")
    }
}

object NavWishList : NavRoute {
    override val route = "wishlist"
}
