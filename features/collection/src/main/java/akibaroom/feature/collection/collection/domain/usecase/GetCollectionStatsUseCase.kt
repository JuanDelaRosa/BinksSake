package akibaroom.feature.collection.collection.domain.usecase

import akibaroom.feature.collection.collection.domain.model.CollectionStats
import akibaroom.feature.collection.collection.domain.repository.CollectionRepository
import javax.inject.Inject

class GetCollectionStatsUseCase @Inject constructor(
    private val repository: CollectionRepository
) {
    suspend operator fun invoke(userId: String): CollectionStats {
        return repository.getCollectionStats(userId)
    }
}
