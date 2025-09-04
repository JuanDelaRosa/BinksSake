package akibaroom.feature.figures.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import akibaroom.feature.figures.data.paging.FiguresPagingSource
import akibaroom.feature.figures.domain.model.Figure
import akibaroom.feature.figures.domain.repository.FigureRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchFigureUseCase @Inject constructor(
    private val figureRepository: FigureRepository
) {
    fun paging(scope: CoroutineScope, pageSize: Int = 20): Flow<PagingData<Figure>> =
        Pager(
            config = PagingConfig(pageSize = pageSize, enablePlaceholders = false),
            pagingSourceFactory = { FiguresPagingSource(figureRepository) }
        ).flow.cachedIn(scope)
}
