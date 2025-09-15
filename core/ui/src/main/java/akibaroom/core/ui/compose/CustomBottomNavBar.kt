package akibaroom.core.ui.compose

import akibaroom.core.ui.theme.Typography
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val isSelected: Boolean = false,
    val onClick: () -> Unit = {}
)

@Composable
fun CustomBottomNavBar(
    items: List<BottomNavItem>,
    showSearch: Boolean,
    modifier: Modifier = Modifier,
    onSearch: () -> Unit = {}
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(contentAlignment = Alignment.Center) {
            GlassBackground(
                color = MaterialTheme.colorScheme.onPrimary,
                isSelected = true,
                cornerRadius = 24.dp
            ) {
                Row {
                    items.forEachIndexed { index, item ->
                        BottomNavTab(item)
                    }
                }
            }
        }
        if (showSearch) {
            Spacer(Modifier.weight(1f))
            SearchButton(onSearch = onSearch)
        }
    }
}


@Composable
private fun SearchButton(onSearch: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .clickable(
                onClick = onSearch,
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ),
        contentAlignment = Alignment.Center
    ) {
        GlassBackground(
            color = MaterialTheme.colorScheme.primary,
            isSelected = true,
            cornerRadius = 30.dp
        ) {
            Column(modifier = Modifier.padding(vertical = 12.dp, horizontal = 12.dp)) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.size(25.dp),
                    tint = MaterialTheme.colorScheme.surface
                )
            }
        }
    }
}

@Composable
private fun BottomNavTab(
    item: BottomNavItem,
    modifier: Modifier = Modifier
) {
    val targetColor = if (item.isSelected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.secondary
    }
    val animatedColor by animateColorAsState(
        targetValue = targetColor,
        label = "iconColor"
    )

    Box(
        modifier = Modifier
            .clickable(
                onClick = item.onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ),
        contentAlignment = Alignment.Center
    ) {
        GlassBackground(
            color = MaterialTheme.colorScheme.secondary,
            isSelected = item.isSelected,
            cornerRadius = 32.dp
        ) {
            Column(
                modifier = modifier.padding(vertical = 4.dp, horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.label,
                    modifier = Modifier.size(28.dp),
                    tint = animatedColor
                )
                Text(
                    text = item.label,
                    style = Typography.labelMedium,
                    fontWeight = if (item.isSelected) FontWeight.Bold else FontWeight.Medium,
                    color = animatedColor,
                )
            }
        }
    }
}

@Preview
@Composable
fun CustomBottomNavBarPreview() {
    CustomBottomNavBar(
        showSearch = true,
        items = listOf(
            BottomNavItem(
                label = "Room",
                icon = Icons.Default.Home,
                isSelected = true
            ),
            BottomNavItem(
                label = "Wishlist",
                icon = Icons.Default.Star,
                isSelected = false
            ),
        )
    )
}
