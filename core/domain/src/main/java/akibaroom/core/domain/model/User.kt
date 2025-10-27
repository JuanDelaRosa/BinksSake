package akibaroom.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    val uid: String,
    val email: String,
    val displayName: String,
    val username: String,
    val userType: UserType,
    val photoUrl: String? = null,
    val bannerUrl: String? = null,
    val bio: String? = null,
    val location: String? = null,
    val website: String? = null,
    val socialLinks: SocialLinks = SocialLinks(),
    val isVerified: Boolean = false,
    val isPrivate: Boolean = false,
    val preferences: UserPreferences = UserPreferences(),
    val stats: UserStats = UserStats(),
    val createdAt: Long = System.currentTimeMillis(),
    val lastLoginAt: Long = System.currentTimeMillis()
)

@Serializable
enum class UserType {
    COLLECTOR,
    STORE,
    ADMIN
}

@Serializable
data class SocialLinks(
    val twitter: String? = null,
    val instagram: String? = null,
    val facebook: String? = null,
    val youtube: String? = null,
    val tiktok: String? = null,
    val discord: String? = null
)

@Serializable
data class UserPreferences(
    val currency: Currency = Currency.USD,
    val language: String = "en",
    val theme: Theme = Theme.SYSTEM,
    val measurementUnit: MeasurementUnit = MeasurementUnit.CM,
    val showNSFW: Boolean = false,
    val notifications: NotificationPreferences = NotificationPreferences()
)

@Serializable
data class NotificationPreferences(
    val achievements: Boolean = true,
    val social: Boolean = true,
    val messages: Boolean = true,
    val priceAlerts: Boolean = true,
    val newFollower: Boolean = true,
    val sales: Boolean = true,
    val quietHoursStart: String? = null,
    val quietHoursEnd: String? = null
)

@Serializable
data class UserStats(
    val totalFigures: Int = 0,
    val totalWishlist: Int = 0,
    val totalValue: Double = 0.0,
    val completedSeries: Int = 0,
    val achievementsUnlocked: Int = 0,
    val followersCount: Int = 0,
    val followingCount: Int = 0,
    val postsCount: Int = 0,
    val figuresUploaded: Int = 0,
    val level: Int = 1,
    val experience: Int = 0
)

@Serializable
enum class Theme {
    LIGHT,
    DARK,
    SYSTEM
}
