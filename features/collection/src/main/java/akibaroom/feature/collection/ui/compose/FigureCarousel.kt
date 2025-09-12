package akibaroom.feature.collection.ui.compose

import akibaroom.core.domain.model.Figure
import akibaroom.core.ui.theme.Typography
import akibaroom.feature.collection.FiguresMoke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FigureCarousel(
    title: String,
    figures: List<Figure>,
    onFigureClick: (Figure) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp, start = 16.dp, bottom = 8.dp),
            text = title,
            style = Typography.titleLarge,
        )
        LazyHorizontalGrid(
            modifier = Modifier.height(200.dp),
            rows = GridCells.Fixed(1),
        ) {
            items(figures) {
                FigureItem(
                    figure = it,
                    onClick = { onFigureClick(it) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FigureCarouselPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        FigureCarousel(
            title = "Sample Title",
            figures = FiguresMoke.figures,
            onFigureClick = { }
        )
    }
}
