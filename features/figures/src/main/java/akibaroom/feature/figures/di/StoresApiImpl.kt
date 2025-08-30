package akibaroom.feature.figures.di

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import akibaroom.feature.figures.api.StoresApi
import akibaroom.feature.figures.ui.StoresViewModel
import akibaroom.feature.figures.ui.navigation.StoresNavigation

class StoresApiImpl: StoresApi {
    @Composable
    override fun Content() {
        val viewModel: StoresViewModel = hiltViewModel()
        StoresNavigation(viewModel = viewModel)
    }
}
