package akibaroom.feature.figures.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.paging.compose.collectAsLazyPagingItems
// removed paging-compose items import to use count/index style
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import akibaroom.core.ui.compose.AsyncImage
import akibaroom.core.ui.compose.BottomNavItem
import akibaroom.core.ui.compose.CustomBottomNavBar
import akibaroom.core.ui.compose.ErrorAlertDialog
import androidx.compose.ui.res.stringResource
import akibaroom.feature.figures.R
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.filled.Home

@Composable
internal fun FiguresScreen(
    state: FigureViewModel.ViewState,
    executeAction: (FigureViewModel.Action) -> Unit,
) {
    BackHandler {
        executeAction(FigureViewModel.Action.BackClicked)
    }
    val listState = rememberSaveable(saver = LazyListState.Saver) {
        LazyListState()
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val lazyPagingItems = state.paging?.collectAsLazyPagingItems()
        if (lazyPagingItems != null) {
            LazyColumn(state = listState) {
                items(count = lazyPagingItems.itemCount) { index ->
                    val ui = lazyPagingItems[index]
                    if (ui != null) {
                        FigureItem(
                            figure = ui,
                            onClick = { }
                        )
                    }
                }
            }
        } else if (state.figures.isNotEmpty()) {
            LazyColumn(state = listState) {
                itemsIndexed(state.figures) { index, figure ->
                    FigureItem(
                        figure = figure,
                        onClick = { executeAction(FigureViewModel.Action.FigureSelected(index)) }
                    )
                }
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(24.dp),
                strokeWidth = 2.dp
            )
        }

        if (state.showError) {
            ErrorAlertDialog(
                message = stringResource(id = R.string.error_generic),
                onDismiss = {
                    executeAction(FigureViewModel.Action.DismissError)
                }
            )
        }
        Column(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            CustomBottomNavBar(
                showSearch = true,
                items = listOf(
                    BottomNavItem(
                        label = "Collection",
                        icon = Icons.Default.Home,
                        isSelected = true
                    ),
                    BottomNavItem(
                        label = "Wishlist",
                        icon = Icons.Default.Star,
                        isSelected = false
                    ),
                )
            )
        }
    }
}

// Removed legacy StoreItem (SakeShop)

@Composable
fun RatingStars(
    rating: Double,
    modifier: Modifier = Modifier,
    maxStars: Int = 5
) {
    val fullStars = rating.toInt()
    val hasHalfStar = (rating - fullStars) >= 0.5
    val emptyStars = maxStars - fullStars - if (hasHalfStar) 1 else 0

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        repeat(fullStars) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color(0xFFFFC107),
                modifier = Modifier.size(16.dp)
            )
        }
        if (hasHalfStar) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.StarHalf,
                contentDescription = null,
                tint = Color(0xFFFFC107),
                modifier = Modifier.size(16.dp)
            )
        }
        repeat(emptyStars) {
            Icon(
                imageVector = Icons.Default.StarBorder,
                contentDescription = null,
                tint = Color(0xFFFFC107),
                modifier = Modifier.size(16.dp)
            )
        }

        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "%.1f".format(rating),
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
internal fun FigureDetailScreen(
    figure: FigureUi,
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

@Preview(showBackground = true)
@Composable
fun FiguresScreenPreview() {
    val sample = listOf(
        FigureUi(id = 1, name = "Rick", image = ""),
        FigureUi(id = 1, name = "Rick", image = ""),
        FigureUi(id = 1, name = "Rick", image = ""),
        FigureUi(id = 1, name = "Rick", image = ""),
        FigureUi(id = 1, name = "Rick", image = ""),
        FigureUi(id = 1, name = "Rick", image = ""),
        FigureUi(id = 1, name = "Rick", image = ""),
        FigureUi(id = 1, name = "Rick", image = ""),
        FigureUi(id = 1, name = "Rick", image = ""),
        FigureUi(id = 1, name = "Rick", image = ""),
        FigureUi(id = 1, name = "Rick", image = ""),
        FigureUi(id = 1, name = "Rick", image = ""),
    )
    FiguresScreen(
        state = FigureViewModel.ViewState(figures = sample),
        executeAction = {}
    )
}

@Preview
@Composable
fun FigureDetailScreenPreview() {
    val sample = FigureUi(id = 1, name = "Rick", image = "")
    FigureDetailScreen(figure = sample, executeAction = {} )
}

@Composable
private fun FigureItem(figure: FigureUi, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = figure.name, style = MaterialTheme.typography.titleMedium)
        }
    }
}
