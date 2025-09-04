package akibaroom.feature.figures.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import kotlinx.coroutines.flow.map
import akibaroom.core.ui.viewmodel.MviViewModel
import akibaroom.core.utils.json.Result
import akibaroom.feature.figures.domain.usecase.FetchFigureUseCase
import akibaroom.feature.figures.data.paging.FiguresPager
import akibaroom.feature.figures.data.mapper.toUi
import akibaroom.core.database.FigureEntity
import akibaroom.core.network.models.Response
import akibaroom.feature.figures.ui.FigureViewModel.Action
import akibaroom.feature.figures.ui.FigureViewModel.ViewEffect
import akibaroom.feature.figures.ui.FigureViewModel.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
internal class FigureViewModel @Inject constructor(
    private val fetchFigureUseCase: FetchFigureUseCase,
    private val figuresPager: FiguresPager
) : MviViewModel<ViewState, ViewEffect, Action>() {

    private fun fetchFigures() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            when (val result = fetchFigureUseCase(1)) {
                is Result.Success -> {
                    _state.update { it.copy(figures = result.data, isLoading = false) }
                }
                is Result.Error -> {
                    _state.update { it.copy(isLoading = false, showError = true) }
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
        val figures: List<FigureUi> = emptyList(),
        val paging: Flow<PagingData<FigureUi>>? = null,
        val isLoading: Boolean = false,
        val showError: Boolean = false
    )

    override fun createInitialState() = ViewState(
        paging = figuresPager
            .pager()
            .map { pagingData: PagingData<FigureEntity> -> pagingData.map { entity: FigureEntity -> entity.toUi() } }
            .cachedIn(viewModelScope)
    )
}
