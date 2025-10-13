package akibaroom.feature.collection.detail.ui.compose

import akibaroom.core.domain.model.Figure
import akibaroom.core.ui.compose.AsyncImage
import akibaroom.core.ui.theme.Dimens
import akibaroom.feature.collection.FiguresMoke
import akibaroom.feature.collection.detail.domain.model.Character
import akibaroom.feature.collection.detail.domain.model.FigureDetails
import akibaroom.feature.collection.detail.ui.viewmodel.FigureDetailViewModel.Action
import akibaroom.feature.collection.detail.ui.viewmodel.FigureDetailViewModel.ViewState
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
internal fun FigureDetailScreen(
    state: ViewState,
    executeAction: (Action) -> Unit,
) {
    BackHandler { executeAction(Action.BackClicked) }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        state.figure?.let { figure ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                AsyncImage(
                    imageUrl = figure.imageUrl,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                )

                Spacer(modifier = Modifier.height(8.dp))

                repeat(20) {
                    figure.name?.let { name ->
                        Text(
                            text = figure.name,
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                }
                Spacer(modifier = Modifier.height(Dimens.BottomNavbarHeight))
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
    }
}

@Preview
@Composable
private fun FigureDetailScreenPreview() {
    FigureDetailScreen(state = ViewState(
        figure = FiguresMoke.figureDetails
    ), executeAction = {} )
}
