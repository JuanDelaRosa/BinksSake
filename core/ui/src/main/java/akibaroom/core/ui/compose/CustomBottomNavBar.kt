package akibaroom.core.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class BottomNavItem(
    val label: String? = null,
    val icon: ImageVector? = null,
    val isSelected: Boolean = false,
    val onClick: () -> Unit = {}
)

@Composable
fun CustomBottomNavBar(
    items: List<BottomNavItem>,
    showSearch: Boolean,
    onSearch: () -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.background(
                Color.White.copy(alpha = 0.1f),
                RoundedCornerShape(32.dp)
            ),
        ) {
            items.forEachIndexed { index, item ->
                BottomNavTab(
                    item = item
                )
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
    Column(
        modifier = Modifier
            .background(
            Color.White.copy(alpha = 0.1f),
            RoundedCornerShape(30.dp)
        )
    ) {
        Column(
            modifier = Modifier.clickable { onSearch() }.padding(vertical = 12.dp, horizontal = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "",
                    Modifier.size(32.dp),
                    tint = Color.White,
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
    Column(
        modifier = modifier.background(
            if (item.isSelected) Color.White.copy(alpha = 0.1f) else Color.Transparent,
            RoundedCornerShape(32.dp)
        ).clickable { item.onClick() }.padding(vertical = 4.dp, horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        item.icon?.let {
            Icon(
                imageVector = item.icon,
                contentDescription = item.label,
                modifier = Modifier.size(28.dp),
                tint = if (item.isSelected) Color.Cyan else Color.White
            )
        }
        item.label?.let {
            Text(
                text = item.label,
                fontSize = 12.sp,
                fontWeight = if (item.isSelected) FontWeight.Medium else FontWeight.Normal,
                color = if (item.isSelected) Color.Cyan else Color.White,
            )
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
                label = "Collection",
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