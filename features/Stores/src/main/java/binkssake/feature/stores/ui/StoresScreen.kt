package binkssake.feature.stores.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import binkssake.core.ui.compose.AsyncImage
import binkssake.core.ui.compose.ErrorAlertDialog
import binkssake.feature.stores.api.model.SakeShop

@Composable
internal fun StoresScreen(
    state: StoresViewModel.ViewState,
    executeAction: (StoresViewModel.Action) -> Unit,
) {
    BackHandler {
        executeAction(StoresViewModel.Action.BackClicked)
    }
    val listState = rememberSaveable(saver = LazyListState.Saver) {
        LazyListState()
    }
    Box {
        LazyColumn(state = listState) {
            itemsIndexed(state.sakeShops) { index, store ->
                StoreItem(
                    store = store,
                    onClick = { executeAction(StoresViewModel.Action.SakeShopSelected(index)) }
                )
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(24.dp),
                strokeWidth = 2.dp
            )
        }

        if (state.showError) {
            ErrorAlertDialog(
                message = "Something went wrong.\nPlease try again.",
                onDismiss = {
                    executeAction(StoresViewModel.Action.DismissError)
                }
            )
        }
    }
}

@Composable
private fun StoreItem(store: SakeShop, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = store.name, style = MaterialTheme.typography.titleMedium)
            Text(text = store.description, style = MaterialTheme.typography.bodyMedium)
            RatingStars(rating = store.rating)
        }
    }
}

@Composable
fun RatingStars(
    rating: Double,
    modifier: Modifier = Modifier,
    maxStars: Int = 5
) {
    val fullStars = rating.toInt()
    val hasHalfStar = (rating - fullStars) >= 0.5
    val emptyStars = maxStars - fullStars - if (hasHalfStar) 1 else 0

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        repeat(fullStars) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color(0xFFFFC107),
                modifier = Modifier.size(16.dp)
            )
        }
        if (hasHalfStar) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.StarHalf,
                contentDescription = null,
                tint = Color(0xFFFFC107),
                modifier = Modifier.size(16.dp)
            )
        }
        repeat(emptyStars) {
            Icon(
                imageVector = Icons.Default.StarBorder,
                contentDescription = null,
                tint = Color(0xFFFFC107),
                modifier = Modifier.size(16.dp)
            )
        }

        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "%.1f".format(rating),
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
internal fun StoreDetailScreen(
    store: SakeShop,
    executeAction: (StoresViewModel.Action) -> Unit,
) {
    BackHandler {
        executeAction(StoresViewModel.Action.BackClicked)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = store.name, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            store.picture?.let {
                AsyncImage(
                    imageUrl = it,
                    modifier = Modifier.fillMaxWidth().height(200.dp),
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = store.description, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            RatingStars(rating = store.rating)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = store.address,
                style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.primary),
                modifier = Modifier.clickable {
                    executeAction(StoresViewModel.Action.AddressClicked(store.googleMapsLink))
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    executeAction(StoresViewModel.Action.WebsiteClicked(store.website))
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Visit Website")
            }
        }
    }
}

@Preview
@Composable
fun StoresScreenPreview() {
    val sampleStores = listOf(
        SakeShop(
            name = "Sake Shop 1",
            description = "A great place for sake.",
            picture = null,
            rating = 4.5,
            address = "123 Sake St, Tokyo",
            googleMapsLink = "https://maps.google.com/?q=35.6895,139.6917",
            website = "https://sakeshop1.example.com"
        ),
        SakeShop(
            name = "Sake Shop 2",
            description = "The best sake in town.",
            picture = null,
            rating = 4.8,
            address = "456 Sake Ave, Kyoto",
            googleMapsLink = "https://maps.google.com/?q=35.0116,135.7681",
            website = "https://sakeshop2.example.com"
        )
    )
    StoresScreen(
        state = StoresViewModel.ViewState(sakeShops = sampleStores),
        executeAction = {}
    )
}

@Preview
@Composable
fun StoreDetailScreenPreview() {
    val sampleStore = SakeShop(
        name = "Sake Shop 1",
        description = "A great place for sake.",
        picture = null,
        rating = 4.5,
        address = "123 Sake St, Tokyo",
        googleMapsLink = "https://maps.google.com/?q=35.6895,139.6917",
        website = "https://sakeshop1.example.com"
    )
    StoreDetailScreen(store = sampleStore, executeAction = {} )
}
