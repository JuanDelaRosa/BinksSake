package akibaroom.feature.figures.di

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import akibaroom.feature.figures.api.StoresApi
import akibaroom.feature.figures.ui.FigureViewModel
import akibaroom.feature.figures.ui.navigation.FiguresNavigation

class FigureApiImpl: StoresApi {
    @Composable
    override fun Content() {
        val viewModel: FigureViewModel = hiltViewModel()
        FiguresNavigation(viewModel = viewModel)
    }
}
