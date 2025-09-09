package akibaroom.feature.collection.di

import akibaroom.feature.collection.api.CollectionApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import akibaroom.feature.collection.ui.CollectionViewModel
import akibaroom.feature.collection.ui.navigation.CollectionNavigation
import akibaroom.feature.collection.ui.navigation.Collection
import akibaroom.feature.collection.ui.navigation.Discover
import akibaroom.feature.collection.ui.navigation.Search
import akibaroom.feature.collection.ui.navigation.WishList
import javax.inject.Inject

class CollectionApiImpl @Inject constructor() : CollectionApi {
    @Composable
    override fun ContentDiscover() {
        val viewModel: CollectionViewModel = hiltViewModel()
        CollectionNavigation(
            startDestination = Discover,
            viewModel = viewModel
        )
    }

    @Composable
    override fun ContentCollection() {
        val viewModel: CollectionViewModel = hiltViewModel()
        CollectionNavigation(
            startDestination = Collection,
            viewModel = viewModel
        )
    }

    @Composable
    override fun ContentWishList() {
        val viewModel: CollectionViewModel = hiltViewModel()
        CollectionNavigation(
            startDestination = WishList,
            viewModel = viewModel
        )
    }

    @Composable
    override fun ContentSearch() {
        val viewModel: CollectionViewModel = hiltViewModel()
        CollectionNavigation(
            startDestination = Search,
            viewModel = viewModel
        )
    }
}
