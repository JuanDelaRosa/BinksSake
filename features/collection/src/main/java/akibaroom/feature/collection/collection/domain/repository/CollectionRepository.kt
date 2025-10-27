package akibaroom.feature.collection.collection.domain.repository

import androidx.paging.PagingData
import akibaroom.core.domain.model.UserFigure
import akibaroom.feature.collection.collection.domain.model.CollectionFilters
import akibaroom.feature.collection.collection.domain.model.CollectionSort
import akibaroom.feature.collection.collection.domain.model.CollectionStats
import akibaroom.feature.collection.collection.domain.model.FilterOptions
import akibaroom.feature.collection.collection.domain.model.UserFigureWithDetails
import kotlinx.coroutines.flow.Flow

interface CollectionRepository {
    fun getUserCollection(
        userId: String,
        filters: CollectionFilters = CollectionFilters(),
        sort: CollectionSort = CollectionSort()
    ): Flow<PagingData<UserFigureWithDetails>>

    suspend fun getCollectionStats(userId: String): CollectionStats

    suspend fun getFilterOptions(userId: String): FilterOptions

    suspend fun getUserFigure(userId: String, figureId: String): UserFigure?

    suspend fun deleteUserFigure(userFigureId: String)

    suspend fun moveToWishlist(userFigureId: String)

    suspend fun moveToCollection(userFigureId: String)

    suspend fun exportCollectionUrl(userId: String): String
}
