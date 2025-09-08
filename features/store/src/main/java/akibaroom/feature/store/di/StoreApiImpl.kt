package akibaroom.feature.store.di

import akibaroom.feature.store.api.StoreApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import akibaroom.feature.store.ui.StoreViewModel
import akibaroom.feature.store.ui.navigation.StoreNavigation
import javax.inject.Inject

class StoreApiImpl @Inject constructor() : StoreApi {
    @Composable
    override fun Content() {
        val viewModel: StoreViewModel = hiltViewModel()
        StoreNavigation(viewModel = viewModel)
    }
}
