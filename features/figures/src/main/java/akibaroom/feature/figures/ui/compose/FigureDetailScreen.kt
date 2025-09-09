package akibaroom.feature.figures.ui.compose

import akibaroom.core.domain.model.Figure
import akibaroom.core.ui.compose.AsyncImage
import akibaroom.feature.figures.ui.FigureViewModel
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
internal fun FigureDetailScreen(
    figure: Figure,
    executeAction: (FigureViewModel.Action) -> Unit,
) {
    BackHandler {
        executeAction(FigureViewModel.Action.BackClicked)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = figure.name, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            AsyncImage(
                imageUrl = figure.image,
                modifier = Modifier.fillMaxWidth().height(200.dp),
            )
            Spacer(modifier = Modifier.height(8.dp))
            // Add more character info if needed
        }
    }
}

@Preview
@Composable
private fun FigureDetailScreenPreview() {
    val sample = Figure(id = 1, name = "Rick", image = "")
    FigureDetailScreen(figure = sample, executeAction = {} )
}
