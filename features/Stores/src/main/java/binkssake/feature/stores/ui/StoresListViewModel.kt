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

    override fun handleAction(action: Action) = when(action) {
        Action.FetchStores -> fetchSakeShops()
        Action.BackClicked -> _effects += ViewEffect.CloseScreen
        is Action.SakeShopSelected -> _effects += ViewEffect.OpenSakeShopDetails(action.sakeShopId)
    }

    sealed interface ViewEffect {
        data object CloseScreen : ViewEffect
        data class OpenSakeShopDetails(val sakeShopId: String) : ViewEffect
    }

    sealed interface Action {
        data object FetchStores : Action
        data object BackClicked : Action
        data class SakeShopSelected(val sakeShopId: String) : Action
    }

    data class ViewState(
        val sakeShops: List<SakeShop> = emptyList(),
        val loading: Boolean = false,
        val showError: Boolean = false
    )

    override fun createInitialState() = ViewState()
}
