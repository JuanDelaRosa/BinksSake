package binkssake.feature.stores.ui

import androidx.lifecycle.viewModelScope
import binkssake.feature.stores.api.model.SakeShop
import binkssake.core.ui.viewmodel.MviViewModel
import binkssake.core.utils.json.Result
import binkssake.feature.stores.domain.usecase.FetchSakeShopsUseCase
import binkssake.feature.stores.ui.StoresViewModel.Action
import binkssake.feature.stores.ui.StoresViewModel.ViewEffect
import binkssake.feature.stores.ui.StoresViewModel.ViewState
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class StoresViewModel(
    private val fetchSakeShopsUseCase: FetchSakeShopsUseCase = FetchSakeShopsUseCase()
) : MviViewModel<ViewState, ViewEffect, Action>() {

    private fun fetchSakeShops() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            when (val result = fetchSakeShopsUseCase()) {
                is Result.Success -> {
                    _state.update { it.copy(sakeShops = result.data, isLoading = false) }
                }
                is Result.Error -> {
                    _state.update { it.copy(isLoading = false, showError = true) }
                }
            }
        }
    }

    override fun handleAction(action: Action) = when (action) {
        Action.FetchStores -> fetchSakeShops()
        Action.BackClicked -> _effects += ViewEffect.NavigateBack
        Action.DismissError -> _state.update { it.copy(showError = false) }
        is Action.SakeShopSelected -> {
            _effects += ViewEffect.OpenSakeShopDetails(action.index)
        }
        is Action.AddressClicked -> {
            _effects += ViewEffect.OpenAddressInMaps(action.address)
        }
        is Action.WebsiteClicked -> {
            _effects += ViewEffect.OpenWebsite(action.website)
        }
    }

    sealed interface ViewEffect {
        data class OpenSakeShopDetails(val index: Int) : ViewEffect
        data object NavigateBack : ViewEffect
        data class OpenAddressInMaps(val address: String) : ViewEffect
        data class OpenWebsite(val website: String) : ViewEffect
    }

    sealed interface Action {
        data object FetchStores : Action
        data object BackClicked : Action
        data object DismissError : Action
        data class SakeShopSelected(val index: Int) : Action
        data class AddressClicked(val address: String) : Action
        data class WebsiteClicked(val website: String) : Action
    }

    data class ViewState(
        val sakeShops: List<SakeShop> = emptyList(),
        val isLoading: Boolean = false,
        val showError: Boolean = false
    )

    override fun createInitialState() = ViewState()
}
