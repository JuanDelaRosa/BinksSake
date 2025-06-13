package binkssake.core.ui.theme.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import binkssake.core.ui.theme.BinksSakeTheme

fun ComposeView.setBinksSakeContent(
    content: @Composable () -> Unit
) {
    setContent {
        BinksSakeTheme {
            content()
        }
    }
}
