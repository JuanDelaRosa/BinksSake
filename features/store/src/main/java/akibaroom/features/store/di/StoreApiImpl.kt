package akibaroom.features.store.di

import akibaroom.features.store.api.StoreApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import akibaroom.features.store.ui.StoreViewModel
import akibaroom.features.store.ui.navigation.StoreNavigation
import javax.inject.Inject

class StoreApiImpl @Inject constructor() : StoreApi {
    @Composable
    override fun Content() {
        val viewModel: StoreViewModel = hiltViewModel()
        StoreNavigation(viewModel = viewModel)
    }
}
