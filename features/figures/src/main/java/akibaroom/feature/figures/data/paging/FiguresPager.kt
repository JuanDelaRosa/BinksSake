package akibaroom.feature.figures.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import akibaroom.core.database.CollectorDatabase
import akibaroom.core.database.FigureEntity
import akibaroom.feature.figures.api.RickAndMortyApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FiguresPager @Inject constructor(
    private val db: CollectorDatabase,
    private val api: RickAndMortyApi
) {
    @OptIn(ExperimentalPagingApi::class)
    fun pager(pageSize: Int = 20): Flow<PagingData<FigureEntity>> =
        Pager(
            config = PagingConfig(pageSize = pageSize, prefetchDistance = 1),
            remoteMediator = FiguresRemoteMediator(db, api),
            pagingSourceFactory = { db.figuresDao().pagingSource() }
        ).flow
}


