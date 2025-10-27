package akibaroom.feature.collection.collection.ui.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import akibaroom.core.domain.model.SortOption
import akibaroom.core.ui.viewmodel.MviViewModel
import akibaroom.feature.collection.collection.domain.model.CollectionFilters
import akibaroom.feature.collection.collection.domain.model.CollectionSort
import akibaroom.feature.collection.collection.domain.model.CollectionStats
import akibaroom.feature.collection.collection.domain.model.FilterOptions
import akibaroom.feature.collection.collection.domain.model.UserFigureWithDetails
import akibaroom.feature.collection.collection.domain.model.ViewMode
import akibaroom.feature.collection.collection.domain.usecase.GetCollectionStatsUseCase
import akibaroom.feature.collection.collection.domain.usecase.GetFilterOptionsUseCase
import akibaroom.feature.collection.collection.domain.usecase.GetUserCollectionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CollectionViewModel @Inject constructor(
    private val getUserCollectionUseCase: GetUserCollectionUseCase,
    private val getCollectionStatsUseCase: GetCollectionStatsUseCase,
    private val getFilterOptionsUseCase: GetFilterOptionsUseCase
) : MviViewModel<CollectionViewModel.ViewState, CollectionViewModel.ViewEffect, CollectionViewModel.Action>() {

    private val _figuresPaging =
        MutableStateFlow<PagingData<UserFigureWithDetails>>(PagingData.empty())
    val figuresPaging: StateFlow<PagingData<UserFigureWithDetails>> = _figuresPaging.asStateFlow()

    init {
        loadCollection()
        loadStats()
        loadFilterOptions()
    }

    override fun handleAction(action: Action) = when (action) {
        Action.BackClicked -> _effects += ViewEffect.NavigateBack
        Action.ToggleViewMode -> toggleViewMode()
        Action.OpenFilters -> _state.value = _state.value.copy(showFiltersSheet = true)
        Action.CloseFilters -> _state.value = _state.value.copy(showFiltersSheet = false)
        Action.OpenSort -> _state.value = _state.value.copy(showSortSheet = true)
        Action.CloseSort -> _state.value = _state.value.copy(showSortSheet = false)
        Action.OpenStats -> _effects += ViewEffect.NavigateToStats
        is Action.ApplyFilters -> applyFilters(action.filters)
        is Action.ApplySort -> applySort(action.sort)
        Action.ClearFilters -> clearFilters()
        is Action.FigureClicked -> _effects += ViewEffect.NavigateToDetail(action.figureId)
        Action.ShareCollection -> shareCollection()
        is Action.SearchQueryChanged -> updateSearchQuery(action.query)
    }

    private fun loadCollection() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                getUserCollectionUseCase(
                    userId = "user1",
                    filters = _state.value.filters,
                    sort = _state.value.sort
                ).cachedIn(viewModelScope)
                    .collect { pagingData ->
                        _figuresPaging.value = pagingData
                        _state.value = _state.value.copy(isLoading = false)
                    }
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    private fun loadStats() {
        viewModelScope.launch {
            try {
                val stats = getCollectionStatsUseCase("user1")
                _state.value = _state.value.copy(stats = stats)
            } catch (e: Exception) {
            }
        }
    }

    private fun loadFilterOptions() {
        viewModelScope.launch {
            try {
                val options = getFilterOptionsUseCase("user1")
                _state.value = _state.value.copy(filterOptions = options)
            } catch (e: Exception) {
            }
        }
    }

    private fun toggleViewMode() {
        val newMode = if (_state.value.viewMode == ViewMode.GRID) ViewMode.LIST else ViewMode.GRID
        _state.value = _state.value.copy(viewMode = newMode)
    }

    private fun applyFilters(filters: CollectionFilters) {
        _state.value = _state.value.copy(
            filters = filters,
            showFiltersSheet = false
        )
        loadCollection()
    }

    private fun applySort(sort: CollectionSort) {
        _state.value = _state.value.copy(
            sort = sort,
            showSortSheet = false
        )
        loadCollection()
    }

    private fun clearFilters() {
        _state.value = _state.value.copy(
            filters = CollectionFilters(),
            showFiltersSheet = false
        )
        loadCollection()
    }

    private fun shareCollection() {
        _effects += ViewEffect.ShareCollection("https://akibaroom.app/collection/user1")
    }

    private fun updateSearchQuery(query: String) {
        val currentFilters = _state.value.filters
        _state.value = _state.value.copy(
            filters = currentFilters.copy(searchQuery = query)
        )
        loadCollection()
    }

    sealed interface ViewEffect {
        data object NavigateBack : ViewEffect
        data class NavigateToDetail(val figureId: String) : ViewEffect
        data object NavigateToStats : ViewEffect
        data class ShareCollection(val url: String) : ViewEffect
    }

    sealed interface Action {
        data object BackClicked : Action
        data object ToggleViewMode : Action
        data object OpenFilters : Action
        data object CloseFilters : Action
        data object OpenSort : Action
        data object CloseSort : Action
        data object OpenStats : Action
        data class ApplyFilters(val filters: CollectionFilters) : Action
        data class ApplySort(val sort: CollectionSort) : Action
        data object ClearFilters : Action
        data class FigureClicked(val figureId: String) : Action
        data object ShareCollection : Action
        data class SearchQueryChanged(val query: String) : Action
    }

    data class ViewState(
        val isLoading: Boolean = false,
        val error: String? = null,
        val viewMode: ViewMode = ViewMode.GRID,
        val filters: CollectionFilters = CollectionFilters(),
        val sort: CollectionSort = CollectionSort(),
        val stats: CollectionStats = CollectionStats(),
        val filterOptions: FilterOptions = FilterOptions(),
        val showFiltersSheet: Boolean = false,
        val showSortSheet: Boolean = false
    )

    override fun createInitialState() = ViewState()
}
