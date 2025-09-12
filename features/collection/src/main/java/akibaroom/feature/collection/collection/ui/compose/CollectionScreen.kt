package akibaroom.feature.collection.collection.ui.compose

import akibaroom.feature.collection.collection.ui.viewmodel.CollectionViewModel.Action
import akibaroom.feature.collection.collection.ui.viewmodel.CollectionViewModel.ViewState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
internal fun CollectionScreen (
    state: ViewState,
    executeAction: (Action) -> Unit
){

}

@Preview
@Composable
fun CollectionScreenPreview() {
    CollectionScreen(
        state = ViewState(),
        executeAction = {}
    )
}
