package akibaroom.feature.collection.collection.domain.model

import akibaroom.core.domain.model.Condition
import akibaroom.core.domain.model.FigureCategory
import akibaroom.core.domain.model.SortOption
import kotlinx.serialization.Serializable

enum class ViewMode {
    GRID,
    LIST
}

@Serializable
data class CollectionFilters(
    val manufacturers: List<String> = emptyList(),
    val series: List<String> = emptyList(),
    val categories: List<FigureCategory> = emptyList(),
    val conditions: List<Condition> = emptyList(),
    val scales: List<String> = emptyList(),
    val priceRange: ClosedFloatingPointRange<Float>? = null,
    val hasBox: Boolean? = null,
    val isSigned: Boolean? = null,
    val searchQuery: String = ""
)

@Serializable
data class CollectionStats(
    val totalFigures: Int = 0,
    val totalValue: Double = 0.0,
    val averagePrice: Double = 0.0,
    val byManufacturer: Map<String, Int> = emptyMap(),
    val bySeries: Map<String, Int> = emptyMap(),
    val byCategory: Map<String, Int> = emptyMap(),
    val byCondition: Map<String, Int> = emptyMap(),
    val byScale: Map<String, Int> = emptyMap(),
    val timeline: List<TimelinePoint> = emptyList(),
    val mostExpensiveFigure: FigureSummary? = null,
    val newestAddition: FigureSummary? = null,
    val completedSeries: List<String> = emptyList()
)

@Serializable
data class TimelinePoint(
    val date: String,
    val count: Int,
    val totalValue: Double
)

@Serializable
data class FigureSummary(
    val id: String,
    val name: String,
    val imageUrl: String,
    val price: Double?,
    val addedAt: Long
)

@Serializable
data class FilterOptions(
    val manufacturers: List<String> = emptyList(),
    val series: List<String> = emptyList(),
    val scales: List<String> = emptyList(),
    val categories: List<FigureCategory> = FigureCategory.entries,
    val conditions: List<Condition> = Condition.entries,
    val priceRange: ClosedFloatingPointRange<Float> = 0f..10000f
)

@Serializable
data class CollectionSort(
    val option: SortOption = SortOption.RECENTLY_ADDED,
    val ascending: Boolean = false
)

data class UserFigureWithDetails(
    val userFigureId: String,
    val figureId: String,
    val userId: String,
    val name: String,
    val manufacturer: String,
    val series: String,
    val character: String,
    val category: FigureCategory,
    val scale: String?,
    val imageUrl: String?,
    val purchasePrice: Double?,
    val purchaseDate: String?,
    val condition: Condition,
    val hasBox: Boolean,
    val isSigned: Boolean,
    val addedAt: Long,
    val rating: Int?
)
