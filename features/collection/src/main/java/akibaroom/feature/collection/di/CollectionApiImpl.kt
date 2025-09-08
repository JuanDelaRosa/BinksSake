package akibaroom.feature.collection.di

import akibaroom.feature.collection.api.CollectionApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import akibaroom.feature.collection.ui.CollectionViewModel
import akibaroom.feature.collection.ui.navigation.CollectionNavigation
import javax.inject.Inject

class CollectionApiImpl @Inject constructor() : CollectionApi {
    @Composable
    override fun Content() {
        val viewModel: CollectionViewModel = hiltViewModel()
        CollectionNavigation(viewModel = viewModel)
    }
}
