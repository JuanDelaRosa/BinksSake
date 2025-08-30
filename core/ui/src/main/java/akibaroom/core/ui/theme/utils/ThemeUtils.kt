package akibaroom.core.ui.theme.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import akibaroom.core.ui.theme.AkibaRoomTheme

fun ComposeView.setAkibaRoomContent(
    content: @Composable () -> Unit
) {
    setContent {
        AkibaRoomTheme {
            content()
        }
    }
}
