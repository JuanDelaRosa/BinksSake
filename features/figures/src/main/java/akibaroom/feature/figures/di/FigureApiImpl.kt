package akibaroom.feature.figures.di

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import akibaroom.feature.figures.api.FiguresApi
import akibaroom.feature.figures.ui.FigureViewModel
//import akibaroom.feature.figures.ui.navigation.FiguresNavigation
import javax.inject.Inject

class FigureApiImpl @Inject constructor() : FiguresApi {
    @Composable
    override fun Content() {
        val viewModel: FigureViewModel = hiltViewModel()
        //FiguresNavigation(viewModel = viewModel)
    }
}
