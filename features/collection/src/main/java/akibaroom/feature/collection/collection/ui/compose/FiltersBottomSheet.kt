package akibaroom.feature.collection.collection.ui.compose

import akibaroom.core.domain.model.Condition
import akibaroom.core.domain.model.FigureCategory
import akibaroom.feature.collection.collection.domain.model.CollectionFilters
import akibaroom.feature.collection.collection.domain.model.FilterOptions
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FiltersBottomSheet(
    filters: CollectionFilters,
    filterOptions: FilterOptions,
    onApply: (CollectionFilters) -> Unit,
    onClear: () -> Unit,
    onDismiss: () -> Unit
) {
    var currentFilters by remember { mutableStateOf(filters) }

    ModalBottomSheet(
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 32.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Filters",
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

            FilterSection(title = "Manufacturer") {
                FilterChipGroup(
                    items = filterOptions.manufacturers,
                    selectedItems = currentFilters.manufacturers,
                    onSelectionChange = { selected ->
                        currentFilters = currentFilters.copy(manufacturers = selected)
                    }
                )
            }

            FilterSection(title = "Series") {
                FilterChipGroup(
                    items = filterOptions.series,
                    selectedItems = currentFilters.series,
                    onSelectionChange = { selected ->
                        currentFilters = currentFilters.copy(series = selected)
                    }
                )
            }

            FilterSection(title = "Category") {
                FilterChipGroup(
                    items = filterOptions.categories.map { it.name },
                    selectedItems = currentFilters.categories.map { it.name },
                    onSelectionChange = { selected ->
                        currentFilters = currentFilters.copy(
                            categories = selected.mapNotNull { name ->
                                FigureCategory.entries.find { it.name == name }
                            }
                        )
                    }
                )
            }

            FilterSection(title = "Condition") {
                FilterChipGroup(
                    items = filterOptions.conditions.map { it.name },
                    selectedItems = currentFilters.conditions.map { it.name },
                    onSelectionChange = { selected ->
                        currentFilters = currentFilters.copy(
                            conditions = selected.mapNotNull { name ->
                                Condition.entries.find { it.name == name }
                            }
                        )
                    }
                )
            }

            FilterSection(title = "Scale") {
                FilterChipGroup(
                    items = filterOptions.scales,
                    selectedItems = currentFilters.scales,
                    onSelectionChange = { selected ->
                        currentFilters = currentFilters.copy(scales = selected)
                    }
                )
            }

            FilterSection(title = "Box Condition") {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterChip(
                        selected = currentFilters.hasBox == true,
                        onClick = {
                            currentFilters = currentFilters.copy(
                                hasBox = if (currentFilters.hasBox == true) null else true
                            )
                        },
                        label = { Text("With Box") }
                    )
                    FilterChip(
                        selected = currentFilters.hasBox == false,
                        onClick = {
                            currentFilters = currentFilters.copy(
                                hasBox = if (currentFilters.hasBox == false) null else false
                            )
                        },
                        label = { Text("Without Box") }
                    )
                }
            }

            FilterSection(title = "Signed") {
                FilterChip(
                    selected = currentFilters.isSigned == true,
                    onClick = {
                        currentFilters = currentFilters.copy(
                            isSigned = if (currentFilters.isSigned == true) null else true
                        )
                    },
                    label = { Text("Signed Only") }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = onClear,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Clear All")
                }

                Button(
                    onClick = { onApply(currentFilters) },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Apply Filters")
                }
            }
        }
    }
}

@Composable
private fun FilterSection(
    title: String,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        content()
    }
}

@Composable
private fun FilterChipGroup(
    items: List<String>,
    selectedItems: List<String>,
    onSelectionChange: (List<String>) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items.forEach { item ->
            FilterChip(
                selected = item in selectedItems,
                onClick = {
                    val newSelection = if (item in selectedItems) {
                        selectedItems - item
                    } else {
                        selectedItems + item
                    }
                    onSelectionChange(newSelection)
                },
                label = { Text(item.replace("_", " ")) }
            )
        }
    }
}
