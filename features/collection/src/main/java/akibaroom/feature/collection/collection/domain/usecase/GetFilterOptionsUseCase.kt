package akibaroom.feature.collection.collection.domain.usecase

import akibaroom.feature.collection.collection.domain.model.FilterOptions
import akibaroom.feature.collection.collection.domain.repository.CollectionRepository
import javax.inject.Inject

class GetFilterOptionsUseCase @Inject constructor(
    private val repository: CollectionRepository
) {
    suspend operator fun invoke(userId: String): FilterOptions {
        return repository.getFilterOptions(userId)
    }
}
