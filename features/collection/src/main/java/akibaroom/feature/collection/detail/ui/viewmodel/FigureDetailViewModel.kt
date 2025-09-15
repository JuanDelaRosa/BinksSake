package akibaroom.feature.collection.detail.ui.viewmodel

import akibaroom.core.domain.model.Figure
import akibaroom.core.network.models.Response
import akibaroom.core.ui.viewmodel.MviViewModel
import akibaroom.feature.collection.detail.domain.usecase.FetchFigureDetailsUseCase
import akibaroom.feature.collection.detail.ui.navigation.FigureDetail
import akibaroom.feature.collection.detail.ui.viewmodel.FigureDetailViewModel.Action
import akibaroom.feature.collection.detail.ui.viewmodel.FigureDetailViewModel.ViewEffect
import akibaroom.feature.collection.detail.ui.viewmodel.FigureDetailViewModel.ViewState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class FigureDetailViewModel @Inject constructor(
    private val fetchFigureDetailsUseCase: FetchFigureDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : MviViewModel<ViewState, ViewEffect, Action>() {

    private val uuid: String = checkNotNull(savedStateHandle[FigureDetail.ARG_UUID])

    init {
        fetchFigure()
    }

    private fun fetchFigure() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            when (val result = fetchFigureDetailsUseCase(uuid)) {
                is Response.Success -> {
                    _state.update {
                        it.copy(isLoading = false, figure = result.data)
                    }
                }
                is Response.Error -> {
                    _state.update {
                        it.copy(isLoading = false)
                    }
                    executeAction(Action.BackClicked)
                }
            }
        }
    }

    override fun handleAction(action: Action) = when (action) {
        Action.BackClicked -> _effects += ViewEffect.NavigateBack
    }

    override fun createInitialState() = ViewState()

    sealed interface ViewEffect {
        data object NavigateBack : ViewEffect
    }

    sealed interface Action {
        data object BackClicked : Action
    }

    data class ViewState(
        val figure: Figure? = null,
        val isLoading: Boolean = false
    )
}
