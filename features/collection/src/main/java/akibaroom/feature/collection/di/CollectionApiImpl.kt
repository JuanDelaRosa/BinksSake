package akibaroom.feature.collection.di

import akibaroom.feature.collection.api.CollectionApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import akibaroom.feature.collection.collection.ui.navigation.Collection
import akibaroom.feature.collection.collection.ui.viewmodel.CollectionViewModel
import akibaroom.feature.collection.discover.ui.navigation.Discover
import akibaroom.feature.collection.search.ui.navigation.Search
import akibaroom.feature.collection.ui.navigation.CollectionNavigation
import akibaroom.feature.collection.wishlist.ui.navigation.WishList
import javax.inject.Inject

class CollectionApiImpl @Inject constructor() : CollectionApi {
    @Composable
    override fun ContentDiscover() {
        CollectionNavigation(startDestination = Discover)
    }

    @Composable
    override fun ContentCollection() {
        CollectionNavigation(startDestination = Collection)
    }

    @Composable
    override fun ContentWishList() {
        CollectionNavigation(startDestination = WishList)
    }

    @Composable
    override fun ContentSearch() {
        CollectionNavigation(startDestination = Search)
    }
}
