package akibaroom.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import akibaroom.core.database.converters.Converters
import akibaroom.core.database.dao.FiguresDao
import akibaroom.core.database.dao.UserCollectionDao
import akibaroom.core.database.entities.*

@Database(
    entities = [
        FigureEntity::class,
        UserCollectionEntity::class,
        StoreEntity::class,
        StoreInventoryEntity::class,
        PreorderEntity::class,
        CustomerEntity::class,
        SaleEntity::class,
        SaleItemEntity::class,
        CacheMetadataEntity::class,
        SearchHistoryEntity::class,
        NotificationEntity::class,
        AchievementEntity::class,
        UserAchievementEntity::class,
        RemoteKeysEntity::class
    ],
    version = 2,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class CollectorDatabase : RoomDatabase() {
    abstract fun figuresDao(): FiguresDao
    abstract fun userCollectionDao(): UserCollectionDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}
