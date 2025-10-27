package akibaroom.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeysEntity(
    @PrimaryKey val figureId: String,
    val prevKey: Int?,
    val nextKey: Int?
)
