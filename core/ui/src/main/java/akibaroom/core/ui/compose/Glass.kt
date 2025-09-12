package akibaroom.core.ui.compose

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun GlassBackground(
    isSelected: Boolean,
    color: Color,
    cornerRadius: Dp = 32.dp,
    blurRadius: Dp = 12.dp,
    borderWidth: Dp = 1.dp,
    content: @Composable BoxScope.() -> Unit
) {
    Box(contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .clip(RoundedCornerShape(cornerRadius))
                .background(
                    brush = if (isSelected) Brush.linearGradient(
                        colors = listOf(
                            color.copy(alpha = 0.3f),
                            color.copy(alpha = 0.8f)
                        )
                    ) else Brush.verticalGradient(listOf(Color.Transparent, Color.Transparent))
                )
                .border(
                    width = borderWidth,
                    color = if (isSelected) MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.2f) else Color.Transparent,
                    shape = RoundedCornerShape(cornerRadius)
                )
                .blur(blurRadius)
        )
        content()
    }
}
