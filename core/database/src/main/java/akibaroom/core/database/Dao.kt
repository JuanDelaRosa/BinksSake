package akibaroom.core.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FiguresDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(entities: List<FigureEntity>)

    @Query("SELECT * FROM figures WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): FigureEntity?

    @Query("SELECT * FROM figures ORDER BY id ASC")
    fun pagingSource(): PagingSource<Int, FigureEntity>

    @Query("DELETE FROM figures")
    suspend fun clearAll()
}

@Dao
interface RemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(keys: List<RemoteKeysEntity>)

    @Query("SELECT * FROM remote_keys WHERE figureId = :id")
    suspend fun remoteKeysById(id: Int): RemoteKeysEntity?

    @Query("DELETE FROM remote_keys")
    suspend fun clearAll()
}


