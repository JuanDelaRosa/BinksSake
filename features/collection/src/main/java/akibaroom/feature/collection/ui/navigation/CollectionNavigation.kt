package akibaroom.feature.collection.ui.navigation

import akibaroom.core.ui.compose.FeatureNavigation
import akibaroom.core.ui.compose.NavRoute
import akibaroom.feature.collection.collection.ui.navigation.collectionGraph
import akibaroom.feature.collection.detail.ui.navigation.figureDetailGraph
import akibaroom.feature.collection.discover.ui.navigation.Discover
import akibaroom.feature.collection.discover.ui.navigation.discoverGraph
import akibaroom.feature.collection.search.ui.navigation.searchGraph
import akibaroom.feature.collection.wishlist.ui.navigation.wishListGraph
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
internal fun CollectionNavigation(startDestination: NavRoute = Discover) {
    val navController = rememberNavController()
    FeatureNavigation(navController = navController, startDestination = startDestination.route) {
        discoverGraph(navController)
        collectionGraph(navController)
        wishListGraph(navController)
        searchGraph(navController)
        figureDetailGraph(navController)
    }
}
