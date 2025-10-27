package akibaroom.core.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_collection",
    foreignKeys = [
        ForeignKey(
            entity = FigureEntity::class,
            parentColumns = ["id"],
            childColumns = ["figureId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("figureId"),
        Index("userId"),
        Index("status"),
        Index("addedAt")
    ]
)
data class UserCollectionEntity(
    @PrimaryKey val id: String,
    val figureId: String,
    val userId: String,
    val status: String,
    val purchasePrice: Double?,
    val purchaseDate: String?,
    val purchaseLocation: String?,
    val storeId: String?,
    val condition: String,
    val hasBox: Boolean,
    val boxCondition: String?,
    val isSigned: Boolean,
    val signedBy: String?,
    val notes: String?,
    val customImages: String,
    val displayLocation: String?,
    val rating: Int?,
    val addedAt: Long,
    val updatedAt: Long
)
