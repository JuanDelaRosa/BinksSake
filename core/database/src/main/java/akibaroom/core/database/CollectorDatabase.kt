package akibaroom.core.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FigureEntity::class, RemoteKeysEntity::class],
    version = 1,
    exportSchema = false
)
abstract class CollectorDatabase : RoomDatabase() {
    abstract fun figuresDao(): FiguresDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}


