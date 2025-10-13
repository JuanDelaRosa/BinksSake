package akibaroom.feature.collection.di

import akibaroom.feature.collection.api.CollectionApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import akibaroom.feature.collection.collection.ui.navigation.collectionGraph
import akibaroom.feature.collection.discover.ui.navigation.discoverGraph
import akibaroom.feature.collection.search.ui.navigation.searchGraph
import akibaroom.feature.collection.wishlist.ui.navigation.wishListGraph
import akibaroom.feature.collection.detail.ui.navigation.figureDetailGraph
import javax.inject.Inject

class CollectionApiImpl @Inject constructor() : CollectionApi {
    override val collectionRoute: String = "collection"
    override val discoverRoute: String = "discover"
    override val searchRoute: String = "search"

    override fun registerGraph(navController: NavController, builder: NavGraphBuilder) {
        with(builder) {
            discoverGraph(navController)
            collectionGraph(navController)
            wishListGraph(navController)
            searchGraph(navController)
            figureDetailGraph(navController)
        }
    }
}
