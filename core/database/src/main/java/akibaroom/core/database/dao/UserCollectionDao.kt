package akibaroom.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import akibaroom.core.database.entities.UserCollectionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserCollectionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: UserCollectionEntity)

    @Query("SELECT * FROM user_collection WHERE userId = :userId AND status = 'OWNED' ORDER BY addedAt DESC")
    fun getUserCollection(userId: String): PagingSource<Int, UserCollectionEntity>

    @Query("SELECT * FROM user_collection WHERE userId = :userId AND status = 'WISHLIST' ORDER BY addedAt DESC")
    fun getUserWishlist(userId: String): PagingSource<Int, UserCollectionEntity>

    @Query("SELECT * FROM user_collection WHERE id = :id LIMIT 1")
    suspend fun getById(id: String): UserCollectionEntity?

    @Query("SELECT * FROM user_collection WHERE userId = :userId AND figureId = :figureId LIMIT 1")
    suspend fun getUserFigure(userId: String, figureId: String): UserCollectionEntity?

    @Query("SELECT COUNT(*) FROM user_collection WHERE userId = :userId AND status = 'OWNED'")
    suspend fun getCollectionCount(userId: String): Int

    @Query("SELECT COUNT(*) FROM user_collection WHERE userId = :userId AND status = 'WISHLIST'")
    suspend fun getWishlistCount(userId: String): Int

    @Query("SELECT SUM(purchasePrice) FROM user_collection WHERE userId = :userId AND status = 'OWNED' AND purchasePrice IS NOT NULL")
    suspend fun getTotalValue(userId: String): Double?

    @Query("UPDATE user_collection SET status = :newStatus, updatedAt = :timestamp WHERE id = :id")
    suspend fun updateStatus(id: String, newStatus: String, timestamp: Long)

    @Query("DELETE FROM user_collection WHERE id = :id")
    suspend fun deleteById(id: String)

    @Transaction
    suspend fun moveToWishlist(id: String) {
        updateStatus(id, "WISHLIST", System.currentTimeMillis())
    }

    @Transaction
    suspend fun moveToCollection(id: String) {
        updateStatus(id, "OWNED", System.currentTimeMillis())
    }
}
