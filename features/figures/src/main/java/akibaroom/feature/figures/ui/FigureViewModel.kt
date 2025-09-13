package akibaroom.feature.figures.ui

import akibaroom.core.domain.model.Figure
import akibaroom.core.ui.viewmodel.MviViewModel
import akibaroom.feature.figures.ui.FigureViewModel.Action
import akibaroom.feature.figures.ui.FigureViewModel.ViewEffect
import akibaroom.feature.figures.ui.FigureViewModel.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class FigureViewModel @Inject constructor(
    //private val fetchFigureUseCase: FetchFigureUseCase
) : MviViewModel<ViewState, ViewEffect, Action>() {

    /*val pagingFlow: Flow<PagingData<Figure>> =
        fetchFigureUseCase.paging(viewModelScope).cachedIn(viewModelScope)*/

    override fun handleAction(action: Action) = when (action) {
        Action.BackClicked -> _effects += ViewEffect.NavigateBack
        Action.DismissError -> _state.update { it.copy(showError = false) }
        is Action.FigureSelected -> {
            _effects += ViewEffect.OpenFigureDetails(action.index)
        }
    }

    sealed interface ViewEffect {
        data class OpenFigureDetails(val index: Int) : ViewEffect
        data object NavigateBack : ViewEffect
    }

    sealed interface Action {
        data object BackClicked : Action
        data object DismissError : Action
        data class FigureSelected(val index: Int) : Action
    }

    data class ViewState(
        val figures: List<Figure> = emptyList(),
        val isLoading: Boolean = false,
        val showError: Boolean = false
    )

    override fun createInitialState() = ViewState()
}
