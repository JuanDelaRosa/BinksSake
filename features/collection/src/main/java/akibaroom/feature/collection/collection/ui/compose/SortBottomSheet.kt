package akibaroom.feature.collection.collection.ui.compose

import akibaroom.core.domain.model.SortOption
import akibaroom.feature.collection.collection.domain.model.CollectionSort
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SortBottomSheet(
    currentSort: CollectionSort,
    onApply: (CollectionSort) -> Unit,
    onDismiss: () -> Unit
) {
    var selectedSort by remember { mutableStateOf(currentSort) }

    ModalBottomSheet(
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 32.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Sort By",
                    style = MaterialTheme.typography.headlineSmall
                )
                IconButton(onClick = onDismiss) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close"
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            SortOption(
                title = "Recently Added",
                isSelected = selectedSort.option == SortOption.RECENTLY_ADDED,
                onClick = {
                    selectedSort = CollectionSort(
                        option = SortOption.RECENTLY_ADDED,
                        ascending = false
                    )
                }
            )

            SortOption(
                title = "Name (A-Z)",
                isSelected = selectedSort.option == SortOption.NAME_ASC,
                onClick = {
                    selectedSort = CollectionSort(
                        option = SortOption.NAME_ASC,
                        ascending = true
                    )
                }
            )

            SortOption(
                title = "Name (Z-A)",
                isSelected = selectedSort.option == SortOption.NAME_DESC,
                onClick = {
                    selectedSort = CollectionSort(
                        option = SortOption.NAME_DESC,
                        ascending = false
                    )
                }
            )

            SortOption(
                title = "Price (Low to High)",
                isSelected = selectedSort.option == SortOption.PRICE_ASC,
                onClick = {
                    selectedSort = CollectionSort(
                        option = SortOption.PRICE_ASC,
                        ascending = true
                    )
                }
            )

            SortOption(
                title = "Price (High to Low)",
                isSelected = selectedSort.option == SortOption.PRICE_DESC,
                onClick = {
                    selectedSort = CollectionSort(
                        option = SortOption.PRICE_DESC,
                        ascending = false
                    )
                }
            )

            SortOption(
                title = "Release Date (Newest)",
                isSelected = selectedSort.option == SortOption.RELEASE_DATE_DESC,
                onClick = {
                    selectedSort = CollectionSort(
                        option = SortOption.RELEASE_DATE_DESC,
                        ascending = false
                    )
                }
            )

            SortOption(
                title = "Release Date (Oldest)",
                isSelected = selectedSort.option == SortOption.RELEASE_DATE_ASC,
                onClick = {
                    selectedSort = CollectionSort(
                        option = SortOption.RELEASE_DATE_ASC,
                        ascending = true
                    )
                }
            )

            SortOption(
                title = "Rating",
                isSelected = selectedSort.option == SortOption.RATING,
                onClick = {
                    selectedSort = CollectionSort(
                        option = SortOption.RATING,
                        ascending = false
                    )
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { onApply(selectedSort) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Apply Sort")
            }
        }
    }
}

@Composable
private fun SortOption(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            color = if (isSelected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.onSurface
            }
        )

        if (isSelected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Selected",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}
