package akibaroom.feature.store.ui

import akibaroom.core.ui.viewmodel.MviViewModel
import akibaroom.feature.store.ui.StoreViewModel.Action
import akibaroom.feature.store.ui.StoreViewModel.ViewEffect
import akibaroom.feature.store.ui.StoreViewModel.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class StoreViewModel @Inject constructor() :
    MviViewModel<ViewState, ViewEffect, Action>() {

    override fun handleAction(action: Action) = when (action) {
        Action.BackClicked -> _effects += ViewEffect.NavigateBack
    }

    sealed interface ViewEffect {
        data object NavigateBack : ViewEffect
    }

    sealed interface Action {
        data object BackClicked : Action
    }

    data class ViewState(
        val isLoading: Boolean = false
    )

    override fun createInitialState() = ViewState()
}