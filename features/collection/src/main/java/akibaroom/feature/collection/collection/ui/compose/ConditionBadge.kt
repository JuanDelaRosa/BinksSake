package akibaroom.feature.collection.collection.ui.compose

import akibaroom.core.domain.model.Condition
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AssistChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun ConditionBadge(
    condition: Condition,
    modifier: Modifier = Modifier
) {
    AssistChip(
        onClick = {},
        label = {
            Text(
                text = condition.name.replace("_", " "),
                style = MaterialTheme.typography.labelSmall
            )
        },
        modifier = modifier.height(24.dp)
    )
}
