package akibaroom.core.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "cache_metadata",
    indices = [Index("cacheKey"), Index("expiresAt")]
)
data class CacheMetadataEntity(
    @PrimaryKey val cacheKey: String,
    val dataType: String,
    val cachedAt: Long,
    val expiresAt: Long,
    val size: Long
)

@Entity(
    tableName = "search_history",
    indices = [Index("userId"), Index("timestamp")]
)
data class SearchHistoryEntity(
    @PrimaryKey val id: String,
    val userId: String,
    val query: String,
    val timestamp: Long
)

@Entity(
    tableName = "notifications",
    indices = [
        Index("userId"),
        Index("isRead"),
        Index("createdAt"),
        Index("type")
    ]
)
data class NotificationEntity(
    @PrimaryKey val id: String,
    val userId: String,
    val type: String,
    val title: String,
    val message: String,
    val iconUrl: String?,
    val imageUrl: String?,
    val actionDataJson: String?,
    val isRead: Boolean,
    val readAt: Long?,
    val createdAt: Long
)

@Entity(
    tableName = "achievements",
    indices = [Index("type"), Index("category")]
)
data class AchievementEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val iconUrl: String,
    val type: String,
    val category: String,
    val requirementJson: String,
    val rewardJson: String?,
    val rarity: String,
    val points: Int,
    val isSecret: Boolean,
    val isRepeatable: Boolean
)

@Entity(
    tableName = "user_achievements",
    indices = [
        Index("userId"),
        Index("achievementId"),
        Index("isCompleted")
    ]
)
data class UserAchievementEntity(
    @PrimaryKey val id: String,
    val userId: String,
    val achievementId: String,
    val progress: Int,
    val isCompleted: Boolean,
    val completedAt: Long?,
    val timesCompleted: Int,
    val lastProgressUpdate: Long
)
