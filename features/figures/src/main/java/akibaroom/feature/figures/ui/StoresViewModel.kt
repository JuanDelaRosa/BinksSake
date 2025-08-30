package akibaroom.feature.figures.ui

import androidx.lifecycle.viewModelScope
import akibaroom.feature.figures.api.model.SakeShop
import akibaroom.core.ui.viewmodel.MviViewModel
import akibaroom.core.utils.json.Result
import akibaroom.feature.figures.domain.usecase.FetchSakeShopsUseCase
import akibaroom.feature.figures.ui.StoresViewModel.Action
import akibaroom.feature.figures.ui.StoresViewModel.ViewEffect
import akibaroom.feature.figures.ui.StoresViewModel.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
internal class StoresViewModel @Inject constructor(
    private val fetchSakeShopsUseCase: FetchSakeShopsUseCase
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
