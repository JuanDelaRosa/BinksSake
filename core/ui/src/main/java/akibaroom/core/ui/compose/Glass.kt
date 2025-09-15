package akibaroom.core.ui.compose

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
    val animationSpec = tween<Color>(
        durationMillis = 500,
        easing = FastOutSlowInEasing
    )

    val startColor1 = if (isSelected) color.copy(alpha = 0.2f) else Color.Transparent
    val startColor2 = if (isSelected) color.copy(alpha = 0.6f) else Color.Transparent
    val animatedColor1 by animateColorAsState(targetValue = startColor1, label = "gradient1", animationSpec = animationSpec)
    val animatedColor2 by animateColorAsState(targetValue = startColor2, label = "gradient2", animationSpec = animationSpec)

    val animatedBrush = Brush.verticalGradient(colors = listOf(animatedColor1, animatedColor2))

    Box(contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .clip(RoundedCornerShape(cornerRadius))
                .background(brush = animatedBrush)
                .border(
                    width = borderWidth,
                    color = if (isSelected) MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.2f) else
                        Color.Transparent,
                    shape = RoundedCornerShape(cornerRadius)
                )
                .blur(blurRadius)
        )
        content()
    }
}
