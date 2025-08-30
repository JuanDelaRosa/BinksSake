package binkssake.feature.stores.di

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import binkssake.feature.stores.api.StoresApi
import binkssake.feature.stores.ui.StoresViewModel
import binkssake.feature.stores.ui.navigation.StoresNavigation

class StoresApiImpl: StoresApi {
    @Composable
    override fun Content() {
        val viewModel: StoresViewModel = viewModel(factory = null)
        StoresNavigation(viewModel = viewModel)
    }
}
