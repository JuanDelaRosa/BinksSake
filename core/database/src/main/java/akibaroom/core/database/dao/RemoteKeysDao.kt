package akibaroom.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import akibaroom.core.database.entities.RemoteKeysEntity

@Dao
interface RemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(keys: List<RemoteKeysEntity>)

    @Query("SELECT * FROM remote_keys WHERE figureId = :figureId")
    suspend fun getRemoteKeys(figureId: String): RemoteKeysEntity?

    @Query("DELETE FROM remote_keys")
    suspend fun clearAll()
}
