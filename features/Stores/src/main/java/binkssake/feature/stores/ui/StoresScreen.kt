package binkssake.feature.stores.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import binkssake.core.ui.compose.AsyncImage
import binkssake.feature.stores.api.model.SakeShop

@Composable
internal fun StoresScreen(
    state: StoresViewModel.ViewState,
    executeAction: (StoresViewModel.Action) -> Unit,
) {
    BackHandler {
        executeAction(StoresViewModel.Action.BackClicked)
    }
    LazyColumn {
        itemsIndexed(state.sakeShops) { index, store ->
            StoreItem(
                store = store,
                onClick = { executeAction(StoresViewModel.Action.SakeShopSelected(index)) }
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
            Text(text = "⭐ ${store.rating}", style = MaterialTheme.typography.labelSmall)
        }
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
            store.picture?.let {
                AsyncImage(
                    imageUrl = it,
                    modifier = Modifier.fillMaxWidth().height(200.dp),
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = store.name, style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "⭐ ${store.rating}", style = MaterialTheme.typography.labelSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = store.description, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Address: ${store.address}")
            Spacer(modifier = Modifier.height(8.dp))
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
            coordinates = Pair(35.6895, 139.6917),
            googleMapsLink = "https://maps.google.com/?q=35.6895,139.6917",
            website = "https://sakeshop1.example.com"
        ),
        SakeShop(
            name = "Sake Shop 2",
            description = "The best sake in town.",
            picture = null,
            rating = 4.8,
            address = "456 Sake Ave, Kyoto",
            coordinates = Pair(35.0116, 135.7681),
            googleMapsLink = "https://maps.google.com/?q=35.0116,135.7681",
            website = "https://sakeshop2.example.com"
        )
    )
    StoresScreen(state = StoresViewModel.ViewState(sakeShops = sampleStores), executeAction = {})
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
        coordinates = Pair(35.6895, 139.6917),
        googleMapsLink = "https://maps.google.com/?q=35.6895,139.6917",
        website = "https://sakeshop1.example.com"
    )
    StoreDetailScreen(store = sampleStore, executeAction = {} )
}
