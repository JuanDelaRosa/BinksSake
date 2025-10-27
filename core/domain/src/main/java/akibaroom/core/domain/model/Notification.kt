package akibaroom.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Notification(
    val id: String,
    val userId: String,
    val type: NotificationType,
    val title: String,
    val message: String,
    val iconUrl: String? = null,
    val imageUrl: String? = null,
    val actionData: NotificationActionData? = null,
    val isRead: Boolean = false,
    val readAt: Long? = null,
    val createdAt: Long = System.currentTimeMillis()
)

@Serializable
enum class NotificationType {
    ACHIEVEMENT_UNLOCKED,
    NEW_FOLLOWER,
    POST_LIKED,
    POST_COMMENTED,
    COMMENT_REPLIED,
    MESSAGE_RECEIVED,
    FIGURE_SOLD,
    SALE_COMPLETED,
    PREORDER_AVAILABLE,
    PREORDER_ARRIVED,
    PRICE_ALERT,
    WISHLIST_AVAILABLE,
    FIGURE_UPLOADED,
    TRANSFER_RECEIVED,
    TRANSFER_ACCEPTED,
    STORE_NEARBY,
    SYSTEM_UPDATE,
    PET_REMINDER
}

@Serializable
data class NotificationActionData(
    val actionType: NotificationActionType,
    val targetId: String,
    val deepLink: String? = null
)

@Serializable
enum class NotificationActionType {
    OPEN_POST,
    OPEN_PROFILE,
    OPEN_FIGURE,
    OPEN_STORE,
    OPEN_CHAT,
    OPEN_ACHIEVEMENT,
    OPEN_SALE,
    OPEN_SETTINGS,
    NONE
}

@Serializable
data class PushNotificationPayload(
    val title: String,
    val body: String,
    val imageUrl: String? = null,
    val data: Map<String, String> = emptyMap()
)
