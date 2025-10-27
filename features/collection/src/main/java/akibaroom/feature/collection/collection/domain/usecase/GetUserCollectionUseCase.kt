package akibaroom.feature.collection.collection.domain.usecase

import androidx.paging.PagingData
import akibaroom.feature.collection.collection.domain.model.CollectionFilters
import akibaroom.feature.collection.collection.domain.model.CollectionSort
import akibaroom.feature.collection.collection.domain.model.UserFigureWithDetails
import akibaroom.feature.collection.collection.domain.repository.CollectionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserCollectionUseCase @Inject constructor(
    private val repository: CollectionRepository
) {
    operator fun invoke(
        userId: String,
        filters: CollectionFilters = CollectionFilters(),
        sort: CollectionSort = CollectionSort()
    ): Flow<PagingData<UserFigureWithDetails>> {
        return repository.getUserCollection(userId, filters, sort)
    }
}
