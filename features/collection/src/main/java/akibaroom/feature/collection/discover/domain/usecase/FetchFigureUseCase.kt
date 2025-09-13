package akibaroom.feature.collection.discover.domain.usecase

import akibaroom.feature.collection.discover.domain.repository.FigureRepository
import javax.inject.Inject

class FetchFigureUseCase @Inject constructor(
    private val figureRepository: FigureRepository
) {
    suspend operator fun invoke() = figureRepository.fetchFigures()
   /* fun paging(scope: CoroutineScope, pageSize: Int = 20): Flow<PagingData<Figure>> =
        Pager(
            config = PagingConfig(pageSize = pageSize, enablePlaceholders = false),
            pagingSourceFactory = { FiguresPagingSource(figureRepository) }
        ).flow.cachedIn(scope)*/
}
