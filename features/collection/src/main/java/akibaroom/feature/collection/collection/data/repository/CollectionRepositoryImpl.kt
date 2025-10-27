package akibaroom.feature.collection.collection.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import akibaroom.core.domain.model.Condition
import akibaroom.core.domain.model.FigureCategory
import akibaroom.core.domain.model.FigureStatus
import akibaroom.core.domain.model.SortOption
import akibaroom.core.domain.model.UserFigure
import akibaroom.feature.collection.collection.domain.model.CollectionFilters
import akibaroom.feature.collection.collection.domain.model.CollectionSort
import akibaroom.feature.collection.collection.domain.model.CollectionStats
import akibaroom.feature.collection.collection.domain.model.FilterOptions
import akibaroom.feature.collection.collection.domain.model.FigureSummary
import akibaroom.feature.collection.collection.domain.model.TimelinePoint
import akibaroom.feature.collection.collection.domain.model.UserFigureWithDetails
import akibaroom.feature.collection.collection.domain.repository.CollectionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class CollectionRepositoryImpl @Inject constructor() : CollectionRepository {

    private val mockFigures = listOf(
        UserFigureWithDetails(
            userFigureId = "uf1",
            figureId = "f1",
            userId = "user1",
            name = "Hatsune Miku: Racing 2021 Ver.",
            manufacturer = "Good Smile Company",
            series = "Vocaloid",
            character = "Hatsune Miku",
            category = FigureCategory.SCALE_FIGURE,
            scale = "1/7",
            imageUrl = "https://images.goodsmile.info/cgm/images/product/20210526/11254/85254/large/0e8e1d9d9f8c1e9a9f8c1e9a9f8c1e9a.jpg",
            purchasePrice = 15000.0,
            purchaseDate = "2024-01-15",
            condition = Condition.MINT,
            hasBox = true,
            isSigned = false,
            addedAt = System.currentTimeMillis() - 86400000,
            rating = 5
        ),
        UserFigureWithDetails(
            userFigureId = "uf2",
            figureId = "f2",
            userId = "user1",
            name = "Rem: Crystal Dress Ver.",
            manufacturer = "Furyu",
            series = "Re:Zero",
            character = "Rem",
            category = FigureCategory.PRIZE_FIGURE,
            scale = null,
            imageUrl = "https://myfigurecollection.net/pics/figure/large/676087.jpg",
            purchasePrice = 3500.0,
            purchaseDate = "2024-02-20",
            condition = Condition.NEAR_MINT,
            hasBox = true,
            isSigned = false,
            addedAt = System.currentTimeMillis() - 172800000,
            rating = 4
        ),
        UserFigureWithDetails(
            userFigureId = "uf3",
            figureId = "f3",
            userId = "user1",
            name = "Saber: Kimono Ver.",
            manufacturer = "Aniplex+",
            series = "Fate/Grand Order",
            character = "Saber",
            category = FigureCategory.SCALE_FIGURE,
            scale = "1/7",
            imageUrl = "https://myfigurecollection.net/pics/figure/large/806237.jpg",
            purchasePrice = 18000.0,
            purchaseDate = "2024-03-10",
            condition = Condition.MINT,
            hasBox = true,
            isSigned = true,
            addedAt = System.currentTimeMillis() - 259200000,
            rating = 5
        ),
        UserFigureWithDetails(
            userFigureId = "uf4",
            figureId = "f4",
            userId = "user1",
            name = "Nendoroid Nezuko Kamado",
            manufacturer = "Good Smile Company",
            series = "Demon Slayer",
            character = "Nezuko Kamado",
            category = FigureCategory.NENDOROID,
            scale = "Non-scale",
            imageUrl = "https://images.goodsmile.info/cgm/images/product/20200228/9292/68292/large/a1b2c3d4e5f6g7h8i9j0k1l2m3n4o5p6.jpg",
            purchasePrice = 4500.0,
            purchaseDate = "2024-04-05",
            condition = Condition.MINT,
            hasBox = true,
            isSigned = false,
            addedAt = System.currentTimeMillis() - 345600000,
            rating = 5
        )
    )

    override fun getUserCollection(
        userId: String,
        filters: CollectionFilters,
        sort: CollectionSort
    ): Flow<PagingData<UserFigureWithDetails>> {
        var filteredFigures = mockFigures.filter { it.userId == userId }

        if (filters.manufacturers.isNotEmpty()) {
            filteredFigures = filteredFigures.filter { it.manufacturer in filters.manufacturers }
        }
        if (filters.series.isNotEmpty()) {
            filteredFigures = filteredFigures.filter { it.series in filters.series }
        }
        if (filters.categories.isNotEmpty()) {
            filteredFigures = filteredFigures.filter { it.category in filters.categories }
        }
        if (filters.conditions.isNotEmpty()) {
            filteredFigures = filteredFigures.filter { it.condition in filters.conditions }
        }
        if (filters.hasBox != null) {
            filteredFigures = filteredFigures.filter { it.hasBox == filters.hasBox }
        }
        if (filters.isSigned != null) {
            filteredFigures = filteredFigures.filter { it.isSigned == filters.isSigned }
        }
        if (filters.searchQuery.isNotBlank()) {
            filteredFigures = filteredFigures.filter {
                it.name.contains(filters.searchQuery, ignoreCase = true) ||
                        it.manufacturer.contains(filters.searchQuery, ignoreCase = true) ||
                        it.series.contains(filters.searchQuery, ignoreCase = true) ||
                        it.character.contains(filters.searchQuery, ignoreCase = true)
            }
        }

        filteredFigures = when (sort.option) {
            SortOption.NAME_ASC -> filteredFigures.sortedBy { it.name }
            SortOption.NAME_DESC -> filteredFigures.sortedByDescending { it.name }
            SortOption.PRICE_ASC -> filteredFigures.sortedBy { it.purchasePrice ?: 0.0 }
            SortOption.PRICE_DESC -> filteredFigures.sortedByDescending { it.purchasePrice ?: 0.0 }
            SortOption.RECENTLY_ADDED -> filteredFigures.sortedByDescending { it.addedAt }
            else -> filteredFigures.sortedByDescending { it.addedAt }
        }

        return flowOf(PagingData.from(filteredFigures))
    }

    override suspend fun getCollectionStats(userId: String): CollectionStats {
        val userFigures = mockFigures.filter { it.userId == userId }
        val totalValue = userFigures.mapNotNull { it.purchasePrice }.sum()

        return CollectionStats(
            totalFigures = userFigures.size,
            totalValue = totalValue,
            averagePrice = if (userFigures.isNotEmpty()) totalValue / userFigures.size else 0.0,
            byManufacturer = userFigures.groupingBy { it.manufacturer }.eachCount(),
            bySeries = userFigures.groupingBy { it.series }.eachCount(),
            byCategory = userFigures.groupingBy { it.category.name }.eachCount(),
            byCondition = userFigures.groupingBy { it.condition.name }.eachCount(),
            byScale = userFigures.groupingBy { it.scale ?: "Unknown" }.eachCount(),
            timeline = listOf(
                TimelinePoint("2024-01", 1, 15000.0),
                TimelinePoint("2024-02", 1, 3500.0),
                TimelinePoint("2024-03", 1, 18000.0),
                TimelinePoint("2024-04", 1, 4500.0)
            ),
            mostExpensiveFigure = userFigures.maxByOrNull { it.purchasePrice ?: 0.0 }?.let {
                FigureSummary(
                    id = it.userFigureId,
                    name = it.name,
                    imageUrl = it.imageUrl ?: "",
                    price = it.purchasePrice,
                    addedAt = it.addedAt
                )
            },
            newestAddition = userFigures.maxByOrNull { it.addedAt }?.let {
                FigureSummary(
                    id = it.userFigureId,
                    name = it.name,
                    imageUrl = it.imageUrl ?: "",
                    price = it.purchasePrice,
                    addedAt = it.addedAt
                )
            },
            completedSeries = listOf()
        )
    }

    override suspend fun getFilterOptions(userId: String): FilterOptions {
        val userFigures = mockFigures.filter { it.userId == userId }

        return FilterOptions(
            manufacturers = userFigures.map { it.manufacturer }.distinct().sorted(),
            series = userFigures.map { it.series }.distinct().sorted(),
            scales = userFigures.mapNotNull { it.scale }.distinct().sorted(),
            categories = FigureCategory.entries,
            conditions = Condition.entries,
            priceRange = 0f..20000f
        )
    }

    override suspend fun getUserFigure(userId: String, figureId: String): UserFigure? {
        val figure = mockFigures.find { it.userId == userId && it.figureId == figureId }
        return figure?.let {
            UserFigure(
                id = it.userFigureId,
                figureId = it.figureId,
                userId = it.userId,
                status = FigureStatus.OWNED,
                purchasePrice = it.purchasePrice,
                purchaseDate = it.purchaseDate,
                condition = it.condition,
                hasBox = it.hasBox,
                isSigned = it.isSigned,
                addedAt = it.addedAt,
                rating = it.rating
            )
        }
    }

    override suspend fun deleteUserFigure(userFigureId: String) {
    }

    override suspend fun moveToWishlist(userFigureId: String) {
    }

    override suspend fun moveToCollection(userFigureId: String) {
    }

    override suspend fun exportCollectionUrl(userId: String): String {
        return "https://akibaroom.app/collection/$userId"
    }
}
