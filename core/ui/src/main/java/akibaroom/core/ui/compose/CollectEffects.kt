package akibaroom.core.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow

@Composable
inline fun <reified E> CollectEffects(
    flow: Flow<E>,
    crossinline onEffect: (E) -> Unit
) {
    LaunchedEffect(Unit) {
        flow.collect { onEffect(it) }
    }
}
