package binkssake.core.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

private const val TAG = "BaseMviViewModel"

abstract class BaseMviViewModel<State, Action> : ViewModel() {

    @Suppress("PropertyName", "VariableNaming")
    protected val _state: MutableStateFlow<State> by lazy { MutableStateFlow(createInitialState()) }
    val state: StateFlow<State> by lazy { _state.asStateFlow() }

    fun executeAction(action: Action) {
        Log.i(TAG, "Action: ${action?.let { it::class.java.name } ?: "?"}")
        handleAction(action)
    }

    protected abstract fun createInitialState(): State

    protected open fun handleAction(action: Action) = Unit

    protected operator fun MutableStateFlow<State>.plusAssign(state: State) {
        tryEmit(state)
    }

    inline fun <reified R : State> MutableStateFlow<State>.updateIf(function: (R) -> R) =
        update { state ->
            when (state) {
                is R -> function(state)
                else -> state
            }
        }

    @Suppress("TooGenericExceptionCaught")
    protected fun CoroutineScope.safeLaunch(
        context: CoroutineContext = EmptyCoroutineContext,
        block: suspend CoroutineScope.() -> Unit
    ): Job = launch(context) {
        try {
            block()
        } catch (e: Exception) {
            ensureActive()
            e.message?.let { Log.e(TAG, it) }
        }
    }

    val handleClickEventsDebounced = debounce<Action>(scope = this.viewModelScope) {
        executeAction(it)
    }
}
