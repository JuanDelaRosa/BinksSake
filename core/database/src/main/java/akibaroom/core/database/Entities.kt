package akibaroom.core.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "figures")
data class FigureEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val imageUrl: String
)

@Entity(tableName = "remote_keys")
data class RemoteKeysEntity(
    @PrimaryKey val figureId: Int,
    val prevKey: Int?,
    val nextKey: Int?
)


