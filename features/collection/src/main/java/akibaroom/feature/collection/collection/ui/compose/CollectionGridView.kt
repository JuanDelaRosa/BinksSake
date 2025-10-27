package akibaroom.feature.collection.collection.ui.compose

import akibaroom.core.ui.compose.AsyncImage
import akibaroom.feature.collection.collection.domain.model.UserFigureWithDetails
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import java.text.NumberFormat
import java.util.Locale

@Composable
internal fun CollectionGridView(
    figures: LazyPagingItems<UserFigureWithDetails>,
    onFigureClick: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(figures.itemCount) { index ->
            figures[index]?.let { figure ->
                FigureGridItem(
                    figure = figure,
                    onClick = { onFigureClick(figure.userFigureId) }
                )
            }
        }
    }
}

@Composable
private fun FigureGridItem(
    figure: UserFigureWithDetails,
    onClick: () -> Unit
) {
    val currencyFormat = NumberFormat.getCurrencyInstance(Locale.US)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.75f)
            ) {
                AsyncImage(
                    imageUrl = figure.imageUrl,
                    contentDescription = figure.name,
                    modifier = Modifier
                        .matchParentSize()
                        .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                )
            }

            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = figure.name,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = figure.manufacturer,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ConditionBadge(condition = figure.condition)

                    figure.purchasePrice?.let { price ->
                        Text(
                            text = currencyFormat.format(price),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                if (figure.rating != null) {
                    Row(
                        modifier = Modifier.padding(top = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            modifier = Modifier.size(14.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = "${figure.rating}/5",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}
