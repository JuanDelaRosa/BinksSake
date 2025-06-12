package binkssake.core.ui.theme.utils

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import binkssake.core.ui.theme.BinksSakeTheme

fun createBinksSakeComposeView(context: Context, content: @Composable () -> Unit) =
    ComposeView(context).apply {
        setBinksSakeContent(content = content)
    }

fun ComposeView.setBinksSakeContent(content: @Composable () -> Unit) {
    setContent {
        BinksSakeTheme {
            content()
        }
    }
}
