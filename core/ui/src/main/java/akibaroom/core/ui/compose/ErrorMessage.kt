package akibaroom.core.ui.compose

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import akibaroom.core.ui.R

@Composable
fun ErrorAlertDialog(
    message: String,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(id = R.string.action_ok))
            }
        },
        title = {
            Text(text = stringResource(id = R.string.error_title))
        },
        text = {
            Text(text = message)
        }
    )
}
