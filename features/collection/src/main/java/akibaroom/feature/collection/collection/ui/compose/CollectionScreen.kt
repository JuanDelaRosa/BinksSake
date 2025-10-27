package akibaroom.feature.collection.collection.ui.compose

import akibaroom.feature.collection.collection.ui.viewmodel.CollectionViewModel.Action
import akibaroom.feature.collection.collection.ui.viewmodel.CollectionViewModel.ViewState
import akibaroom.feature.collection.collection.domain.model.ViewMode
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import akibaroom.feature.collection.collection.domain.model.UserFigureWithDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CollectionScreen(
    state: ViewState,
    figuresPaging: Flow<PagingData<UserFigureWithDetails>>,
    executeAction: (Action) -> Unit
) {
    val figures = figuresPaging.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            CollectionTopBar(
                stats = state.stats,
                viewMode = state.viewMode,
                onViewModeToggle = { executeAction(Action.ToggleViewMode) },
                onFilterClick = { executeAction(Action.OpenFilters) },
                onSortClick = { executeAction(Action.OpenSort) },
                onStatsClick = { executeAction(Action.OpenStats) },
                onShareClick = { executeAction(Action.ShareCollection) }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when {
                state.isLoading && figures.itemCount == 0 -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                figures.itemCount == 0 -> {
                    EmptyCollectionView(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                else -> {
                    when (state.viewMode) {
                        ViewMode.GRID -> CollectionGridView(
                            figures = figures,
                            onFigureClick = { executeAction(Action.FigureClicked(it)) }
                        )

                        ViewMode.LIST -> CollectionListView(
                            figures = figures,
                            onFigureClick = { executeAction(Action.FigureClicked(it)) }
                        )
                    }
                }
            }
        }
    }

    if (state.showFiltersSheet) {
        FiltersBottomSheet(
            filters = state.filters,
            filterOptions = state.filterOptions,
            onApply = { executeAction(Action.ApplyFilters(it)) },
            onClear = { executeAction(Action.ClearFilters) },
            onDismiss = { executeAction(Action.CloseFilters) }
        )
    }

    if (state.showSortSheet) {
        SortBottomSheet(
            currentSort = state.sort,
            onApply = { executeAction(Action.ApplySort(it)) },
            onDismiss = { executeAction(Action.CloseSort) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CollectionTopBar(
    stats: akibaroom.feature.collection.collection.domain.model.CollectionStats,
    viewMode: ViewMode,
    onViewModeToggle: () -> Unit,
    onFilterClick: () -> Unit,
    onSortClick: () -> Unit,
    onStatsClick: () -> Unit,
    onShareClick: () -> Unit
) {
    val currencyFormat = NumberFormat.getCurrencyInstance(Locale.US)

    TopAppBar(
        title = {
            Column {
                Text(
                    text = "My Collection",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "${stats.totalFigures} figures • ${currencyFormat.format(stats.totalValue)}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        actions = {
            IconButton(onClick = onViewModeToggle) {
                Icon(
                    imageVector = if (viewMode == ViewMode.GRID) {
                        Icons.Default.ViewList
                    } else {
                        Icons.Default.GridView
                    },
                    contentDescription = "Toggle view mode"
                )
            }

            IconButton(onClick = onFilterClick) {
                Icon(
                    imageVector = Icons.Default.FilterList,
                    contentDescription = "Filters"
                )
            }

            IconButton(onClick = onSortClick) {
                Icon(
                    imageVector = Icons.Default.Sort,
                    contentDescription = "Sort"
                )
            }

            IconButton(onClick = onStatsClick) {
                Icon(
                    imageVector = Icons.Default.BarChart,
                    contentDescription = "Statistics"
                )
            }

            IconButton(onClick = onShareClick) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share collection"
                )
            }
        }
    )
}

@Composable
private fun EmptyCollectionView(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.CollectionsBookmark,
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Your collection is empty",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Start adding figures to build your collection",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview
@Composable
fun CollectionScreenPreview() {
    MaterialTheme {
        CollectionScreen(
            state = ViewState(),
            figuresPaging = flowOf(PagingData.empty()),
            executeAction = {}
        )
    }
}
