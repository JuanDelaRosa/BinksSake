package akibaroom.feature.collection.api

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

interface CollectionApi {
    val collectionRoute: String
    val discoverRoute: String
    val searchRoute: String

    /** Register this feature's navigation graph into the root NavHost. */
    fun registerGraph(navController: NavController, builder: NavGraphBuilder)
}
