package akibaroom.feature.figures.ui

import akibaroom.core.ui.viewmodel.MviViewModel
import akibaroom.feature.figures.domain.model.Figure
import akibaroom.feature.figures.domain.usecase.FetchFigureUseCase
import akibaroom.feature.figures.ui.FigureViewModel.Action
import akibaroom.feature.figures.ui.FigureViewModel.ViewEffect
import akibaroom.feature.figures.ui.FigureViewModel.ViewState
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class FigureViewModel @Inject constructor(
    private val fetchFigureUseCase: FetchFigureUseCase
) : MviViewModel<ViewState, ViewEffect, Action>() {

    val pagingFlow: Flow<PagingData<Figure>> =
        fetchFigureUseCase.paging(viewModelScope).cachedIn(viewModelScope)

    override fun handleAction(action: Action) = when (action) {
        Action.FetchFigures -> { /* Paging starts automatically via flow */ }
        Action.BackClicked -> _effects += ViewEffect.NavigateBack
        Action.DismissError -> _state.update { it.copy(showError = false) }
        is Action.FigureSelected -> {
            _effects += ViewEffect.OpenFigureDetails(action.index)
        }
        is Action.AddressClicked -> {
            _effects += ViewEffect.OpenAddressInMaps(action.address)
        }
        is Action.WebsiteClicked -> {
            _effects += ViewEffect.OpenWebsite(action.website)
        }
    }

    sealed interface ViewEffect {
        data class OpenFigureDetails(val index: Int) : ViewEffect
        data object NavigateBack : ViewEffect
        data class OpenAddressInMaps(val address: String) : ViewEffect
        data class OpenWebsite(val website: String) : ViewEffect
    }

    sealed interface Action {
        data object FetchFigures : Action
        data object BackClicked : Action
        data object DismissError : Action
        data class FigureSelected(val index: Int) : Action
        data class AddressClicked(val address: String) : Action
        data class WebsiteClicked(val website: String) : Action
    }

    data class ViewState(
        val figures: List<Figure> = emptyList(),
        val isLoading: Boolean = false,
        val showError: Boolean = false
    )

    override fun createInitialState() = ViewState()
}
