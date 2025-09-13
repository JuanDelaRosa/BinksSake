package akibaroom.feature.collection.search.ui.navigation

import akibaroom.core.ui.compose.NavRoute
import android.app.Activity
import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.searchGraph(navController: NavController) {
    composable(Search.route) {
        // Placeholder Collection Screen
        Text("Search Screen - Coming Soon")
    }
}

object Search : NavRoute {
    override val route = "search"
}
