package akibaroom.feature.figures.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import akibaroom.core.database.CollectorDatabase
import akibaroom.core.database.FigureEntity
import akibaroom.core.database.RemoteKeysEntity
import akibaroom.feature.figures.api.RickAndMortyApi

@OptIn(ExperimentalPagingApi::class)
class FiguresRemoteMediator(
    private val db: CollectorDatabase,
    private val api: RickAndMortyApi
) : RemoteMediator<Int, FigureEntity>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, FigureEntity>): MediatorResult {
        try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull() ?: return MediatorResult.Success(endOfPaginationReached = true)
                    val key = db.remoteKeysDao().remoteKeysById(lastItem.id)?.nextKey
                    key ?: return MediatorResult.Success(endOfPaginationReached = true)
                }
            }

            val response = api.getCharacters(page)
            val endOfPaginationReached = response.results.isEmpty()

            db.withTransactionCompat {
                if (loadType == LoadType.REFRESH) {
                    db.remoteKeysDao().clearAll()
                    db.figuresDao().clearAll()
                }
                val entities = response.results.map { FigureEntity(id = it.id, name = it.name, imageUrl = it.image) }
                val prevKey = if (page == 1) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = entities.map { RemoteKeysEntity(figureId = it.id, prevKey = prevKey, nextKey = nextKey) }
                db.remoteKeysDao().upsertAll(keys)
                db.figuresDao().upsertAll(entities)
            }

            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (t: Throwable) {
            return MediatorResult.Error(t)
        }
    }
}

// Inline helper to avoid androidX.room:room-ktx dependency leakage from here
private suspend fun CollectorDatabase.withTransactionCompat(block: suspend () -> Unit) {
    // If room-ktx is available in this module, could use runInTransaction. Fallback to transaction.
    this.beginTransaction()
    try {
        block()
        this.setTransactionSuccessful()
    } finally {
        this.endTransaction()
    }
}


