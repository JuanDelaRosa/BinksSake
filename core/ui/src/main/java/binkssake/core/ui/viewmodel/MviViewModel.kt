package binkssake.core.ui.viewmodel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

fun <T> Flow<T>.observe(
    lifecycleOwner: LifecycleOwner,
    state: Lifecycle.State = Lifecycle.State.STARTED,
    action: suspend (value: T) -> Unit
) = lifecycleOwner.lifecycleScope.launch {
    lifecycleOwner.repeatOnLifecycle(state) {
        collect { action(it) }
    }
}

abstract class MviViewModel<State, Effect, Action> : BaseMviViewModel<State, Action>() {

    @Suppress("PropertyName", "VariableNaming")
    protected val _effects: MutableSharedFlow<Effect> by lazy { MutableSharedFlow() }
    val effects: SharedFlow<Effect> by lazy { _effects.asSharedFlow() }

    protected operator fun MutableSharedFlow<Effect>.plusAssign(effect: Effect) {
        viewModelScope.launch { emit(effect) }
    }
}
