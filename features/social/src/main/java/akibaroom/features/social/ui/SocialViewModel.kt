package akibaroom.features.social.ui

import akibaroom.core.ui.viewmodel.MviViewModel
import akibaroom.features.social.ui.SocialViewModel.Action
import akibaroom.features.social.ui.SocialViewModel.ViewEffect
import akibaroom.features.social.ui.SocialViewModel.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class SocialViewModel @Inject constructor() :
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