# Collection Feature - Gestión de Colección Personal

## 🎯 Objetivo

Permitir a los usuarios gestionar su colección personal de figuras, visualizarla de diferentes
formas, aplicar filtros avanzados y ver estadísticas detalladas.

## 📦 Funcionalidades

### Vista de Room (Colección)

- **Grid/List Toggle** - Cambiar entre vista de cuadrícula y lista
- **Filtros Avanzados** - Por manufacturer, series, category, scale
- **Ordenamiento** - Por nombre, fecha agregada, precio, custom
- **Búsqueda** - Búsqueda rápida dentro de la colección
- **Estadísticas** - Total figuras, valor total, series completas

### Detalle de Figura

- **Información Completa** - Todos los datos de la figura
- **Galería Personal** - Fotos propias de la figura
- **Notas Privadas** - Notas personales editables
- **Datos de Compra** - Precio, tienda, fecha
- **Condición** - Estado de figura y caja
- **Metadata** - Si está firmada, localización display
- **Acciones** - Editar, Eliminar, Mover a Wishlist

### Exportar/Compartir

- **Exportar PDF** - Lista completa con fotos
- **Exportar Excel** - Hoja de cálculo con datos
- **Compartir Colección** - Link público a colección
- **Estadísticas Visuales** - Gráficas de distribución

## 🎨 UI Components

### CollectionScreen

```kotlin
@Composable
fun CollectionScreen(
    state: CollectionState,
    onAction: (CollectionAction) -> Unit
) {
    Scaffold(
        topBar = {
            CollectionTopBar(
                stats = state.stats,
                viewMode = state.viewMode,
                onViewModeChange = { onAction(CollectionAction.ChangeViewMode(it)) },
                onFilterClick = { onAction(CollectionAction.OpenFilters) },
                onSortClick = { onAction(CollectionAction.OpenSort) }
            )
        }
    ) { padding ->
        Box(Modifier.padding(padding)) {
            when {
                state.isLoading -> LoadingView()
                state.isEmpty -> EmptyCollectionView()
                else -> CollectionContent(
                    figures = state.figures,
                    viewMode = state.viewMode,
                    onFigureClick = { onAction(CollectionAction.FigureClicked(it)) }
                )
            }
        }
    }
}
```

### CollectionTopBar

```kotlin
@Composable
fun CollectionTopBar(
    stats: CollectionStats,
    viewMode: ViewMode,
    onViewModeChange: (ViewMode) -> Unit,
    onFilterClick: () -> Unit,
    onSortClick: () -> Unit
) {
    TopAppBar(
        title = {
            Column {
                Text("My Collection")
                Text(
                    "${stats.totalFigures} figures • $${stats.totalValue}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        },
        actions = {
            // View mode toggle
            IconButton(onClick = { 
                onViewModeChange(
                    if (viewMode == ViewMode.GRID) ViewMode.LIST else ViewMode.GRID
                )
            }) {
                Icon(
                    if (viewMode == ViewMode.GRID) Icons.Default.ViewList 
                    else Icons.Default.GridView
                )
            }
            
            // Filters
            IconButton(onClick = onFilterClick) {
                Icon(Icons.Default.FilterList)
            }
            
            // Sort
            IconButton(onClick = onSortClick) {
                Icon(Icons.Default.Sort)
            }
            
            // More options
            IconButton(onClick = { /* Menu */ }) {
                Icon(Icons.Default.MoreVert)
            }
        }
    )
}
```

### Grid View

```kotlin
@Composable
fun CollectionGridView(
    figures: List<UserFigure>,
    onFigureClick: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = figures,
            key = { it.id }
        ) { figure ->
            FigureGridItem(
                figure = figure,
                onClick = { onFigureClick(figure.id) }
            )
        }
    }
}
```

### FigureGridItem

```kotlin
@Composable
fun FigureGridItem(
    figure: UserFigure,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.75f)
            .clickable(onClick = onClick)
    ) {
        Column {
            // Image
            AsyncImage(
                model = figure.imageUrl,
                contentDescription = figure.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentScale = ContentScale.Crop
            )
            
            // Info
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = figure.name,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Condition badge
                    ConditionBadge(condition = figure.condition)
                    
                    // Price
                    if (figure.purchasePrice != null) {
                        Text(
                            text = "$${figure.purchasePrice}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}
```

### List View

```kotlin
@Composable
fun CollectionListView(
    figures: List<UserFigure>,
    onFigureClick: (String) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = figures,
            key = { it.id }
        ) { figure ->
            FigureListItem(
                figure = figure,
                onClick = { onFigureClick(figure.id) }
            )
        }
    }
}
```

### FigureListItem

```kotlin
@Composable
fun FigureListItem(
    figure: UserFigure,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Image
            AsyncImage(
                model = figure.imageUrl,
                contentDescription = figure.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            
            // Info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = figure.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2
                )
                
                Text(
                    text = "${figure.manufacturer} • ${figure.series}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                Spacer(Modifier.height(4.dp))
                
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    ConditionBadge(condition = figure.condition)
                    
                    if (figure.hasBox) {
                        Chip(text = "With Box")
                    }
                    
                    if (figure.isSigned) {
                        Chip(text = "Signed")
                    }
                }
            }
            
            // Price and date
            Column(
                horizontalAlignment = Alignment.End
            ) {
                if (figure.purchasePrice != null) {
                    Text(
                        text = "$${figure.purchasePrice}",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                
                Text(
                    text = figure.addedAt.toDateString(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
```

### Filters Bottom Sheet

```kotlin
@Composable
fun FiltersBottomSheet(
    filters: CollectionFilters,
    onApply: (CollectionFilters) -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                "Filters",
                style = MaterialTheme.typography.headlineSmall
            )
            
            Spacer(Modifier.height(16.dp))
            
            // Manufacturer filter
            FilterSection(title = "Manufacturer") {
                ManufacturerChips(
                    selected = filters.manufacturers,
                    onSelectionChange = { /* update */ }
                )
            }
            
            // Series filter
            FilterSection(title = "Series") {
                SeriesChips(
                    selected = filters.series,
                    onSelectionChange = { /* update */ }
                )
            }
            
            // Category filter
            FilterSection(title = "Category") {
                CategoryChips(
                    selected = filters.categories,
                    onSelectionChange = { /* update */ }
                )
            }
            
            // Condition filter
            FilterSection(title = "Condition") {
                ConditionChips(
                    selected = filters.conditions,
                    onSelectionChange = { /* update */ }
                )
            }
            
            // Price range
            FilterSection(title = "Price Range") {
                RangeSlider(
                    value = filters.priceRange,
                    onValueChange = { /* update */ },
                    valueRange = 0f..1000f
                )
            }
            
            // Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = { /* Clear filters */ },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Clear")
                }
                
                Button(
                    onClick = { onApply(filters) },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Apply")
                }
            }
        }
    }
}
```

### Statistics View

```kotlin
@Composable
fun CollectionStats(
    stats: CollectionStats
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Overview cards
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                StatCard(
                    title = "Total Figures",
                    value = "${stats.totalFigures}",
                    modifier = Modifier.weight(1f)
                )
                
                StatCard(
                    title = "Total Value",
                    value = "$${stats.totalValue}",
                    modifier = Modifier.weight(1f)
                )
            }
        }
        
        // By manufacturer chart
        item {
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp)) {
                    Text(
                        "By Manufacturer",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    Spacer(Modifier.height(16.dp))
                    
                    PieChart(
                        data = stats.byManufacturer,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                }
            }
        }
        
        // By series chart
        item {
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp)) {
                    Text(
                        "By Series",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    Spacer(Modifier.height(16.dp))
                    
                    BarChart(
                        data = stats.bySeries,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                }
            }
        }
        
        // Timeline
        item {
            Card(Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp)) {
                    Text(
                        "Collection Growth",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    Spacer(Modifier.height(16.dp))
                    
                    LineChart(
                        data = stats.timeline,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                }
            }
        }
    }
}
```

## 🔄 ViewModel

```kotlin
@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val getUserCollectionUseCase: GetUserCollectionUseCase,
    private val deleteFigureUseCase: DeleteFigureUseCase,
    private val moveToWishlistUseCase: MoveToWishlistUseCase,
    private val getCollectionStatsUseCase: GetCollectionStatsUseCase
) : MviViewModel<CollectionState, CollectionEffect, CollectionAction>() {
    
    init {
        loadCollection()
        loadStats()
    }
    
    private fun loadCollection() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            
            getUserCollectionUseCase(currentUserId)
                .cachedIn(viewModelScope)
                .collect { pagingData ->
                    _state.update {
                        it.copy(
                            figures = pagingData,
                            isLoading = false
                        )
                    }
                }
        }
    }
    
    private fun loadStats() {
        viewModelScope.launch {
            val stats = getCollectionStatsUseCase(currentUserId)
            _state.update { it.copy(stats = stats) }
        }
    }
    
    override fun handleAction(action: CollectionAction) = when (action) {
        is CollectionAction.FigureClicked -> navigateToDetail(action.figureId)
        is CollectionAction.ChangeViewMode -> changeViewMode(action.mode)
        CollectionAction.OpenFilters -> openFilters()
        is CollectionAction.ApplyFilters -> applyFilters(action.filters)
        CollectionAction.OpenSort -> openSort()
        is CollectionAction.ApplySort -> applySort(action.sort)
        is CollectionAction.DeleteFigure -> deleteFigure(action.figureId)
        is CollectionAction.MoveToWishlist -> moveToWishlist(action.figureId)
        CollectionAction.ExportPDF -> exportPDF()
        CollectionAction.ExportExcel -> exportExcel()
        CollectionAction.ShareCollection -> shareCollection()
    }
}
```

## 📊 State Management

```kotlin
data class CollectionState(
    val figures: PagingData<UserFigure> = PagingData.empty(),
    val stats: CollectionStats = CollectionStats(),
    val viewMode: ViewMode = ViewMode.GRID,
    val filters: CollectionFilters = CollectionFilters(),
    val sortBy: SortOption = SortOption.DATE_ADDED_DESC,
    val isLoading: Boolean = false,
    val showFilters: Boolean = false,
    val showSort: Boolean = false
)

data class CollectionStats(
    val totalFigures: Int = 0,
    val totalValue: Double = 0.0,
    val byManufacturer: Map<String, Int> = emptyMap(),
    val bySeries: Map<String, Int> = emptyMap(),
    val byCategory: Map<String, Int> = emptyMap(),
    val timeline: List<TimelinePoint> = emptyList()
)

enum class ViewMode {
    GRID,
    LIST
}
```

## 🎯 Use Cases

```kotlin
class GetUserCollectionUseCase @Inject constructor(
    private val collectionRepository: CollectionRepository
) {
    operator fun invoke(userId: String): Flow<PagingData<UserFigure>> {
        return collectionRepository.getUserCollection(userId)
    }
}

class GetCollectionStatsUseCase @Inject constructor(
    private val collectionRepository: CollectionRepository
) {
    suspend operator fun invoke(userId: String): CollectionStats {
        return collectionRepository.getCollectionStats(userId)
    }
}
```

## 📖 Referencias

- [Paging 3 with Compose](https://developer.android.com/jetpack/compose/lists#large-datasets)
- [LazyVerticalGrid](https://developer.android.com/jetpack/compose/lists#lazy-grids)
- [ModalBottomSheet](https://developer.android.com/jetpack/compose/components/bottom-sheets)
