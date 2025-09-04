package akibaroom.feature.figures.ui

import akibaroom.core.network.models.Response
import akibaroom.core.ui.viewmodel.MviViewModel
import akibaroom.feature.figures.domain.model.Figure
import akibaroom.feature.figures.domain.usecase.FetchFigureUseCase
import akibaroom.feature.figures.ui.FigureViewModel.Action
import akibaroom.feature.figures.ui.FigureViewModel.ViewEffect
import akibaroom.feature.figures.ui.FigureViewModel.ViewState
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class FigureViewModel @Inject constructor(
    private val fetchFigureUseCase: FetchFigureUseCase = FetchFigureUseCase()
) : MviViewModel<ViewState, ViewEffect, Action>() {

    private fun fetchFigures() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            when (val result = fetchFigureUseCase(1)) {
                is Response.Error -> {
                    _state.update { it.copy(isLoading = false, showError = true) }
                }
                is Response.Success -> {
                    _state.update { it.copy(figures = result.data.results, isLoading = false) }
                }
            }
        }
    }

    override fun handleAction(action: Action) = when (action) {
        Action.FetchFigures -> fetchFigures()
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
