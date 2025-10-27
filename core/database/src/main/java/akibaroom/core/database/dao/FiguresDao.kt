package akibaroom.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import akibaroom.core.database.entities.FigureEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FiguresDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(figure: FigureEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(figures: List<FigureEntity>)

    @Update
    suspend fun update(figure: FigureEntity)

    @Query("SELECT * FROM figures WHERE id = :id LIMIT 1")
    suspend fun getById(id: String): FigureEntity?

    @Query("SELECT * FROM figures WHERE mfcId = :mfcId LIMIT 1")
    suspend fun getByMfcId(mfcId: String): FigureEntity?

    @Query("SELECT * FROM figures WHERE jan = :jan LIMIT 1")
    suspend fun getByJan(jan: String): FigureEntity?

    @Query("SELECT * FROM figures WHERE barcode = :barcode LIMIT 1")
    suspend fun getByBarcode(barcode: String): FigureEntity?

    @Query("SELECT * FROM figures ORDER BY name ASC")
    fun getAllPaged(): PagingSource<Int, FigureEntity>

    @Query("SELECT * FROM figures ORDER BY uploadedAt DESC LIMIT :limit")
    fun getRecent(limit: Int = 20): Flow<List<FigureEntity>>

    @Query(
        """
        SELECT * FROM figures 
        WHERE name LIKE '%' || :query || '%' 
           OR manufacturer LIKE '%' || :query || '%'
           OR series LIKE '%' || :query || '%'
           OR character LIKE '%' || :query || '%'
        ORDER BY name ASC
    """
    )
    fun search(query: String): PagingSource<Int, FigureEntity>

    @Query(
        """
        SELECT * FROM figures 
        WHERE (:manufacturer IS NULL OR manufacturer = :manufacturer)
          AND (:series IS NULL OR series = :series)
          AND (:category IS NULL OR category = :category)
          AND (:isNSFW = 0 OR isNSFW = :isNSFW)
        ORDER BY name ASC
    """
    )
    fun filterFigures(
        manufacturer: String?,
        series: String?,
        category: String?,
        isNSFW: Boolean
    ): PagingSource<Int, FigureEntity>

    @Query("SELECT DISTINCT manufacturer FROM figures ORDER BY manufacturer ASC")
    suspend fun getAllManufacturers(): List<String>

    @Query("SELECT DISTINCT series FROM figures ORDER BY series ASC")
    suspend fun getAllSeries(): List<String>

    @Query("SELECT DISTINCT category FROM figures")
    suspend fun getAllCategories(): List<String>

    @Query("SELECT COUNT(*) FROM figures")
    suspend fun count(): Int

    @Query("DELETE FROM figures WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("DELETE FROM figures")
    suspend fun deleteAll()
}
