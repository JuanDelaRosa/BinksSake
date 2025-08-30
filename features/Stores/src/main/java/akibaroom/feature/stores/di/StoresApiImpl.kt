package akibaroom.feature.stores.di

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import akibaroom.feature.stores.api.StoresApi
import akibaroom.feature.stores.ui.StoresViewModel
import akibaroom.feature.stores.ui.navigation.StoresNavigation

class StoresApiImpl: StoresApi {
    @Composable
    override fun Content() {
        val viewModel: StoresViewModel = hiltViewModel()
        StoresNavigation(viewModel = viewModel)
    }
}
