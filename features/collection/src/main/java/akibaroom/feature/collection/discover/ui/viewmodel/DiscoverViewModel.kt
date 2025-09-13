package akibaroom.feature.collection.discover.ui.viewmodel

import akibaroom.core.domain.model.Figure
import akibaroom.core.network.models.Response
import akibaroom.core.ui.viewmodel.MviViewModel
import akibaroom.feature.collection.FiguresMoke
import akibaroom.feature.collection.discover.domain.model.DiscoverSection
import akibaroom.feature.collection.discover.domain.usecase.FetchFigureUseCase
import akibaroom.feature.collection.discover.ui.viewmodel.DiscoverViewModel.Action
import akibaroom.feature.collection.discover.ui.viewmodel.DiscoverViewModel.ViewEffect
import akibaroom.feature.collection.discover.ui.viewmodel.DiscoverViewModel.ViewState
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class DiscoverViewModel @Inject constructor(
    private val fetchFigureUseCase: FetchFigureUseCase
) : MviViewModel<ViewState, ViewEffect, Action>() {

    init {
        fetchSections()
    }

    private fun fetchSections() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            when (val result = fetchFigureUseCase()) {
                is Response.Success -> {
                    _state.update {
                        it.copy(isLoading = false, sections = result.data)
                    }
                }
                is Response.Error -> {
                    _state.update {
                        it.copy(isLoading = false, showError = true)
                    }
                }
            }
        }
    }

    override fun handleAction(action: Action) = when (action) {
        Action.BackClicked -> _effects += ViewEffect.NavigateBack
        Action.DismissError -> _state.update { it.copy(showError = false) }
        is Action.FigureClick -> {
            _effects += ViewEffect.OpenFigureDetails(action.figureId)
        }
    }

    override fun createInitialState() = ViewState()

    sealed interface ViewEffect {
        data class OpenFigureDetails(val figureId: Int) : ViewEffect
        data object NavigateBack : ViewEffect
    }

    sealed interface Action {
        data object BackClicked : Action
        data object DismissError : Action
        data class FigureClick(val figureId: Int) : Action
    }

    data class ViewState(
        val sections: List<DiscoverSection> = emptyList(),
        val isLoading: Boolean = false,
        val showError: Boolean = false
    )
}
