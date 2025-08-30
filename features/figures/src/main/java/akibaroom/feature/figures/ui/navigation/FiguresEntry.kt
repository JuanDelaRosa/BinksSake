package akibaroom.feature.figures.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import akibaroom.feature.figures.ui.FigureViewModel

@Composable
fun FiguresEntry() {
    val viewModel: FigureViewModel = hiltViewModel()
    FiguresNavigation(viewModel = viewModel)
}


