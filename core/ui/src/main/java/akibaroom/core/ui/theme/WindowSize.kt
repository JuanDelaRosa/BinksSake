package akibaroom.core.ui.theme

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

data class CollectorWindowSize(
    val width: WindowWidthSizeClass,
    val height: WindowHeightSizeClass
)

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun rememberCollectorWindowSize(): CollectorWindowSize {
    val view = LocalView.current
    val width = view.width.takeIf { it > 0 } ?: view.resources.displayMetrics.widthPixels
    val height = view.height.takeIf { it > 0 } ?: view.resources.displayMetrics.heightPixels
    val density = view.resources.displayMetrics.density
    val sizeClass = WindowSizeClass.calculateFromSize(
        DpSize((width / density).dp, (height / density).dp)
    )
    return CollectorWindowSize(
        width = sizeClass.widthSizeClass,
        height = sizeClass.heightSizeClass
    )
}

fun CollectorWindowSize.isCompact(): Boolean = width == WindowWidthSizeClass.Compact
fun CollectorWindowSize.isMedium(): Boolean = width == WindowWidthSizeClass.Medium
fun CollectorWindowSize.isExpanded(): Boolean = width == WindowWidthSizeClass.Expanded


