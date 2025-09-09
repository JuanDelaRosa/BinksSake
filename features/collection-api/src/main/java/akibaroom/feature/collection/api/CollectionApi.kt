package akibaroom.feature.collection.api

import androidx.compose.runtime.Composable

interface CollectionApi {
    @Composable
    fun ContentDiscover()
    @Composable
    fun ContentCollection()
    @Composable
    fun ContentWishList()
    @Composable
    fun ContentSearch()
}
