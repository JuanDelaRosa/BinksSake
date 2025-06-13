package binkssake.core.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun AsyncImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {
    Box(modifier = modifier) {
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build()
        )
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = modifier.matchParentSize(),
            contentScale = ContentScale.Crop
        )
        when(painter.state) {
            is AsyncImagePainter.State.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(24.dp),
                    strokeWidth = 2.dp
                )
            }
            is AsyncImagePainter.State.Error -> {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Error loading image",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(24.dp)
                )
            }
            else -> Unit
        }
    }
}
