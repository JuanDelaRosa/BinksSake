package binkssake.feature.stores.ui

import androidx.lifecycle.viewModelScope
import binkssake.feature.stores.api.model.SakeShop
import binkssake.core.ui.viewmodel.MviViewModel
import binkssake.core.utils.json.Result
import binkssake.feature.stores.domain.usecase.FetchSakeShopsUseCase
import binkssake.feature.stores.ui.StoresListViewModel.Action
import binkssake.feature.stores.ui.StoresListViewModel.ViewEffect
import binkssake.feature.stores.ui.StoresListViewModel.ViewState
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class StoresListViewModel(
    private val fetchSakeShopsUseCase: FetchSakeShopsUseCase = FetchSakeShopsUseCase()
) : MviViewModel<ViewState, ViewEffect, Action>() {

    private fun fetchSakeShops() {
        viewModelScope.launch {
            _state.update { it.copy(loading = true) }
            when (val result = fetchSakeShopsUseCase()) {
                is Result.Success -> {
                    _state.update { it.copy(sakeShops = result.data, loading = false) }
                }
                is Result.Error -> {
                    _state.update { it.copy(loading = false, showError = true) }
                }
            }
        }
    }

    override fun handleAction(action: Action) = when (action) {
        Action.FetchStores -> fetchSakeShops()
        Action.BackClicked -> _effects += ViewEffect.NavigateBack
        is Action.SakeShopSelected -> {
            _state.update { it.copy(selected = action.sakeShop) }
            _effects += ViewEffect.OpenSakeShopDetails(action.sakeShop)
        }
    }

    sealed interface ViewEffect {
        data class OpenSakeShopDetails(val sakeShop: SakeShop) : ViewEffect
        data object NavigateBack : ViewEffect
    }

    sealed interface Action {
        data object FetchStores : Action
        data object BackClicked : Action
        data class SakeShopSelected(val sakeShop: SakeShop) : Action
    }

    data class ViewState(
        val sakeShops: List<SakeShop> = emptyList(),
        val selected: SakeShop? = null,
        val loading: Boolean = false,
        val showError: Boolean = false
    )

    override fun createInitialState() = ViewState()
}
