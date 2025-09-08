package akibaroom.features.collection.di

import akibaroom.features.collection.api.CollectionApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import akibaroom.features.collection.ui.CollectionViewModel
import akibaroom.features.collection.ui.navigation.CollectionNavigation
import javax.inject.Inject

class CollectionApiImpl @Inject constructor() : CollectionApi {
    @Composable
    override fun Content() {
        val viewModel: CollectionViewModel = hiltViewModel()
        CollectionNavigation(viewModel = viewModel)
    }
}
