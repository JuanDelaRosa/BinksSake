package akibaroom.core.ui.compose

import akibaroom.core.ui.theme.Typography
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomTopBar(
    title: String,
    onIconClick: () -> Unit = {}
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = Typography.headlineLarge,
            )
            Spacer(Modifier.weight(1f))
            ProfileButton(onIconClick = onIconClick)
        }
        HorizontalDivider(
            Modifier.background(MaterialTheme.colorScheme.primary),
            DividerDefaults.Thickness,
            MaterialTheme.colorScheme.primaryContainer
        )
    }
}


@Composable
private fun ProfileButton(onIconClick: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .background(
                MaterialTheme.colorScheme.primary.copy(alpha = 0.8f), RoundedCornerShape(30.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() }
                ) { onIconClick() }
                .padding(vertical = 12.dp, horizontal = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "",
                    Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.surface,
                )
            }
        }
    }
}

@Preview
@Composable
fun CustomTopBarPreview() {
    CustomTopBar(title = "Custom Top Bar")
}