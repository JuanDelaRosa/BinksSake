package akibaroom.core.ui.theme.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import akibaroom.core.ui.theme.BinksSakeTheme

fun ComposeView.setBinksSakeContent(
    content: @Composable () -> Unit
) {
    setContent {
        BinksSakeTheme {
            content()
        }
    }
}
