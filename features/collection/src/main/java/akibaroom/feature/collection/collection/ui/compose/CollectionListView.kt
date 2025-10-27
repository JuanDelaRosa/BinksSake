package akibaroom.feature.collection.collection.ui.compose

import akibaroom.core.ui.compose.AsyncImage
import akibaroom.feature.collection.collection.domain.model.UserFigureWithDetails
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
internal fun CollectionListView(
    figures: LazyPagingItems<UserFigureWithDetails>,
    onFigureClick: (String) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(figures.itemCount) { index ->
            figures[index]?.let { figure ->
                FigureListItem(
                    figure = figure,
                    onClick = { onFigureClick(figure.userFigureId) }
                )
            }
        }
    }
}

@Composable
private fun FigureListItem(
    figure: UserFigureWithDetails,
    onClick: () -> Unit
) {
    val currencyFormat = NumberFormat.getCurrencyInstance(Locale.US)
    val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
            ) {
                AsyncImage(
                    imageUrl = figure.imageUrl,
                    contentDescription = figure.name,
                    modifier = Modifier
                        .matchParentSize()
                        .clip(RoundedCornerShape(8.dp))
                )
            }

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = figure.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "${figure.manufacturer} • ${figure.series}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ConditionBadge(condition = figure.condition)

                    if (figure.hasBox) {
                        AssistChip(
                            onClick = {},
                            label = { Text("Box", style = MaterialTheme.typography.labelSmall) },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    modifier = Modifier.size(14.dp)
                                )
                            },
                            modifier = Modifier.height(24.dp)
                        )
                    }

                    if (figure.isSigned) {
                        AssistChip(
                            onClick = {},
                            label = { Text("Signed", style = MaterialTheme.typography.labelSmall) },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = null,
                                    modifier = Modifier.size(14.dp)
                                )
                            },
                            modifier = Modifier.height(24.dp)
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

            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                figure.purchasePrice?.let { price ->
                    Text(
                        text = currencyFormat.format(price),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = dateFormat.format(Date(figure.addedAt)),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
