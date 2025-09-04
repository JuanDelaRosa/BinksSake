package akibaroom.feature.figures.ui.compose

import akibaroom.core.ui.compose.BottomNavItem
import akibaroom.core.ui.compose.CustomBottomNavBar
import akibaroom.core.ui.compose.ErrorAlertDialog
import akibaroom.feature.figures.R
import akibaroom.feature.figures.domain.model.Figure
import akibaroom.feature.figures.ui.FigureViewModel
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.flowOf
import akibaroom.core.ui.theme.rememberCollectorWindowSize
import androidx.compose.runtime.LaunchedEffect

@Composable
internal fun FiguresScreen(
    pagingFlow: LazyPagingItems<Figure>,
    state: FigureViewModel.ViewState,
    executeAction: (FigureViewModel.Action) -> Unit,
) {
    BackHandler { executeAction(FigureViewModel.Action.BackClicked) }
    Box(modifier = Modifier.fillMaxSize()) {
        val density = LocalDensity.current
        var bottomBarHeightPx by remember { mutableStateOf(0) }
        val windowSize = rememberCollectorWindowSize()
        LazyVerticalGrid(
            columns = GridCells.Fixed(windowSize.gridCellsFixed()),
            contentPadding = PaddingValues(bottom = with(density) { bottomBarHeightPx.toDp() })
        ) {
            items(pagingFlow.itemCount) { index ->
                val figure = pagingFlow[index]
                if (figure != null) {
                    FigureItem(
                        figure = figure,
                        onClick = { executeAction(FigureViewModel.Action.FigureSelected(index)) }
                    )
                }
            }
        }
        Column(modifier = Modifier.align(Alignment.BottomCenter)) {
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
            , modifier = Modifier.onGloballyPositioned { coordinates ->
                    bottomBarHeightPx = coordinates.size.height
                }
            )
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
    }
}

@Preview(showBackground = true)
@Composable
private fun FiguresScreenPreview() {
    val sample = listOf(
        Figure(id = 1, name = "Rick", image = ""),
        Figure(id = 1, name = "Rick", image = ""),
        Figure(id = 1, name = "Rick", image = ""),
        Figure(id = 1, name = "Rick", image = ""),
        Figure(id = 1, name = "Rick", image = ""),
        Figure(id = 1, name = "Rick", image = ""),
        Figure(id = 1, name = "Rick", image = ""),
        Figure(id = 1, name = "Rick", image = ""),
        Figure(id = 1, name = "Rick", image = ""),
        Figure(id = 1, name = "Rick", image = ""),
        Figure(id = 1, name = "Rick", image = ""),
        Figure(id = 1, name = "Rick", image = ""),
    )
    val pagingItems = remember { flowOf(PagingData.from(sample)) }.collectAsLazyPagingItems()
    FiguresScreen(
        pagingFlow = pagingItems,
        state = FigureViewModel.ViewState(figures = sample),
        executeAction = {}
    )
}
