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
        viewModelScope.launch {
            when (val result = fetchSakeShopsUseCase()) {
                is Result.Success -> {
                    _state.update { it.copy(sakeShops = result.data) }
                }
                is Result.Error -> Unit
            }
        }
    }

    override fun handleAction(action: Action) = when (action) {
        Action.FetchStores -> fetchSakeShops()
        Action.BackClicked -> _effects += ViewEffect.NavigateBack
        is Action.SakeShopSelected -> {
            _effects += ViewEffect.OpenSakeShopDetails(action.index)
        }
    }

    sealed interface ViewEffect {
        data class OpenSakeShopDetails(val index: Int) : ViewEffect
        data object NavigateBack : ViewEffect
    }

    sealed interface Action {
        data object FetchStores : Action
        data object BackClicked : Action
        data class SakeShopSelected(val index: Int) : Action
    }

    data class ViewState(
        val sakeShops: List<SakeShop> = emptyList()
    )

    override fun createInitialState() = ViewState()
}
