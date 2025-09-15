package akibaroom.feature.collection.api

import androidx.compose.runtime.Composable

interface CollectionApi {
    val collectionRoute: String
    val discoverRoute: String
    val searchRoute: String

    @Composable
    fun ContentDiscover()
    @Composable
    fun ContentCollection()
    @Composable
    fun ContentWishList()
    @Composable
    fun ContentSearch()
}
