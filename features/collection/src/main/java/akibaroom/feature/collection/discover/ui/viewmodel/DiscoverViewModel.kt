package akibaroom.feature.collection.discover.ui.viewmodel

import akibaroom.core.domain.model.Figure
import akibaroom.core.ui.viewmodel.MviViewModel
import akibaroom.feature.collection.FiguresMoke
import akibaroom.feature.collection.discover.domain.model.DiscoverSection
import akibaroom.feature.collection.discover.ui.viewmodel.DiscoverViewModel.Action
import akibaroom.feature.collection.discover.ui.viewmodel.DiscoverViewModel.ViewEffect
import akibaroom.feature.collection.discover.ui.viewmodel.DiscoverViewModel.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class DiscoverViewModel @Inject constructor(
) : MviViewModel<ViewState, ViewEffect, Action>() {

    override fun handleAction(action: Action) = when (action) {
        Action.BackClicked -> _effects += ViewEffect.NavigateBack
        Action.DismissError -> _state.update { it.copy(showError = false) }
        is Action.FigureClick -> {
            _effects += ViewEffect.OpenFigureDetails(action.figure)
        }
    }

    override fun createInitialState() = ViewState()

    sealed interface ViewEffect {
        data class OpenFigureDetails(val figure: Figure) : ViewEffect
        data object NavigateBack : ViewEffect
    }

    sealed interface Action {
        data object BackClicked : Action
        data object DismissError : Action
        data class FigureClick(val figure: Figure) : Action
    }

    data class ViewState(
        val sections: List<DiscoverSection> = FiguresMoke.sections,//emptyList(),
        val isLoading: Boolean = false,
        val showError: Boolean = false
    )
}
