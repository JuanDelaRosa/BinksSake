package akibaroom.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchQuery(
    val text: String = "",
    val manufacturers: List<String> = emptyList(),
    val series: List<String> = emptyList(),
    val characters: List<String> = emptyList(),
    val categories: List<FigureCategory> = emptyList(),
    val priceRange: PriceRange? = null,
    val releaseDateRange: DateRange? = null,
    val scale: String? = null,
    val includeNSFW: Boolean = false,
    val sortBy: SortOption = SortOption.RELEVANCE,
    val onlyInStock: Boolean = false,
    val storeIds: List<String> = emptyList()
)

@Serializable
data class PriceRange(
    val min: Double,
    val max: Double,
    val currency: Currency = Currency.USD
)

@Serializable
data class DateRange(
    val start: String,
    val end: String
)

@Serializable
enum class SortOption {
    RELEVANCE,
    NAME_ASC,
    NAME_DESC,
    PRICE_ASC,
    PRICE_DESC,
    RELEASE_DATE_ASC,
    RELEASE_DATE_DESC,
    POPULARITY,
    RECENTLY_ADDED,
    RATING
}

@Serializable
data class SearchSuggestion(
    val text: String,
    val type: SuggestionType,
    val metadata: Map<String, String> = emptyMap()
)

@Serializable
enum class SuggestionType {
    FIGURE,
    SERIES,
    MANUFACTURER,
    CHARACTER,
    CATEGORY,
    USER,
    STORE
}

@Serializable
data class SearchHistory(
    val id: String,
    val userId: String,
    val query: String,
    val timestamp: Long = System.currentTimeMillis()
)

@Serializable
data class SavedSearch(
    val id: String,
    val userId: String,
    val name: String,
    val query: SearchQuery,
    val notifyOnNewResults: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)

@Serializable
data class FilterOptions(
    val availableManufacturers: List<FilterOption>,
    val availableSeries: List<FilterOption>,
    val availableCategories: List<FigureCategory>,
    val availableScales: List<String>,
    val priceRange: PriceRange
)

@Serializable
data class FilterOption(
    val value: String,
    val label: String,
    val count: Int
)
