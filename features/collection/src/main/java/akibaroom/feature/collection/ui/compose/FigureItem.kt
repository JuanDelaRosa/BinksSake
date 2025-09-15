package akibaroom.feature.collection.ui.compose

import akibaroom.core.domain.model.Figure
import akibaroom.core.ui.compose.AsyncImage
import akibaroom.core.ui.theme.Typography
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FigureItem(figure: Figure, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() }
            .width(150.dp)
            .height(220.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                imageUrl = figure.image,
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.1f),
                                Color.Black.copy(alpha = 0.6f),
                            )
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = figure.name,
                    maxLines = 1,
                    style = Typography.labelLarge,
                    color = Color.White,
                )
                Text(
                    text = figure.name,
                    maxLines = 1,
                    style = Typography.labelSmall,
                    color = Color.White,
                )
            }
        }
    }
}

@Preview
@Composable
fun FigureItemPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        FigureItem(
            figure = Figure(
                id = "",
                name = "Figure 1",
                image = "https://picsum.photos/200"
            ),
            onClick = {}
        )
    }
}
