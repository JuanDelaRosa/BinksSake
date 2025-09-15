package akibaroom.feature.collection.discover.ui.compose

import akibaroom.core.ui.compose.SearchBarButton
import akibaroom.core.ui.theme.Dimens
import akibaroom.feature.collection.FiguresMoke
import akibaroom.feature.collection.discover.ui.viewmodel.DiscoverViewModel.Action
import akibaroom.feature.collection.discover.ui.viewmodel.DiscoverViewModel.ViewState
import akibaroom.feature.collection.ui.compose.FigureCarousel
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
internal fun DiscoverScreen(
    state: ViewState,
    executeAction: (Action) -> Unit
) {
    BackHandler { executeAction(Action.BackClicked) }
    LazyColumn(
        contentPadding = PaddingValues(bottom = Dimens.BottomNavbarHeight),
        modifier = Modifier.fillMaxWidth().wrapContentHeight()
    )
    {
        item {
            SearchBarButton("Search figures") { executeAction(Action.SearchClicked) }
        }
        items(state.sections) { section ->
            FigureCarousel(
                section.title,
                section.list
            ) {
                executeAction(Action.FigureClick(it.id))
            }
        }
    }
}

@Preview
@Composable
private fun DiscoverScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        DiscoverScreen(
            state = ViewState(
                sections = FiguresMoke.sections
            ),
            executeAction = {}
        )
    }
}
