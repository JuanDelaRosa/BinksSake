package akibaroom.feature.collection.ui.compose

import akibaroom.core.domain.model.Figure
import akibaroom.core.ui.theme.Typography
import akibaroom.core.ui.theme.rememberCollectorWindowSize
import akibaroom.feature.collection.FiguresMoke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FigureGrid(
    figures: List<Figure>,
    onFigureClick: (Figure) -> Unit
) {
    val windowSize = rememberCollectorWindowSize()
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(windowSize.gridCellsFixed())
    ) {
        items(figures) {
            FigureItem(
                figure = it,
                onClick = { onFigureClick(it) }
            )
        }
    }
}

@Preview
@Composable
fun FigureGridPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        FigureGrid(
            figures = FiguresMoke.figures,
            onFigureClick = {}
        )
    }
}
