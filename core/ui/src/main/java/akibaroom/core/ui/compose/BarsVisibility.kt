package akibaroom.core.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.staticCompositionLocalOf

/** Controller to read and modify the visibility of top/bottom bars from feature screens. */
class BarsVisibilityController(
    val isVisibleState: MutableState<Boolean>
) {
    val isVisible: Boolean get() = isVisibleState.value
    fun setVisible(visible: Boolean) { isVisibleState.value = visible }
}

/** CompositionLocal provider for [BarsVisibilityController]. Must be provided at app root. */
val LocalBarsVisibilityController = staticCompositionLocalOf<BarsVisibilityController> {
    error("BarsVisibilityController not provided")
}

/**
 * Side-effect helper to set bars visibility while this composable is in the composition.
 * It restores the previous value on dispose.
 */
@Composable
fun BarsVisibilityEffect(visible: Boolean) {
    val controller = LocalBarsVisibilityController.current
    DisposableEffect(visible) {
        val previous = controller.isVisible
        controller.setVisible(visible)
        onDispose { controller.setVisible(previous) }
    }
}


