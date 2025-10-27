package akibaroom.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Achievement(
    val id: String,
    val name: String,
    val description: String,
    val iconUrl: String,
    val type: AchievementType,
    val category: AchievementCategory,
    val requirement: AchievementRequirement,
    val reward: AchievementReward? = null,
    val rarity: AchievementRarity,
    val points: Int,
    val isSecret: Boolean = false,
    val isRepeatable: Boolean = false
)

@Serializable
enum class AchievementType {
    COLLECTION_SIZE,
    FIGURE_UPLOADER,
    SOCIAL_ENGAGEMENT,
    PURCHASE_FREQUENCY,
    SERIES_COMPLETION,
    MANUFACTURER_COLLECTION,
    CATEGORY_COLLECTION,
    SPECIAL_EVENT,
    TIME_BASED,
    COMMUNITY
}

@Serializable
enum class AchievementCategory {
    BRONZE,
    SILVER,
    GOLD,
    PLATINUM,
    DIAMOND
}

@Serializable
enum class AchievementRarity {
    COMMON,
    UNCOMMON,
    RARE,
    EPIC,
    LEGENDARY
}

@Serializable
data class AchievementRequirement(
    val metric: String,
    val targetValue: Int,
    val currentValue: Int = 0
)

@Serializable
data class AchievementReward(
    val experiencePoints: Int = 0,
    val badge: String? = null,
    val title: String? = null,
    val specialFeature: String? = null
)

@Serializable
data class UserAchievement(
    val id: String,
    val userId: String,
    val achievementId: String,
    val progress: Int = 0,
    val isCompleted: Boolean = false,
    val completedAt: Long? = null,
    val timesCompleted: Int = 0,
    val lastProgressUpdate: Long = System.currentTimeMillis()
)

@Serializable
data class AchievementNotification(
    val achievementId: String,
    val achievementName: String,
    val achievementIcon: String,
    val message: String,
    val timestamp: Long = System.currentTimeMillis()
)
