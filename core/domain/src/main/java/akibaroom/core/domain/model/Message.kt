package akibaroom.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Conversation(
    val id: String,
    val participants: List<String>,
    val lastMessage: Message? = null,
    val unreadCount: Int = 0,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

@Serializable
data class Message(
    val id: String,
    val conversationId: String,
    val senderId: String,
    val senderName: String,
    val senderPhotoUrl: String? = null,
    val content: String,
    val type: MessageType = MessageType.TEXT,
    val mediaUrl: String? = null,
    val sharedFigure: SharedFigure? = null,
    val isRead: Boolean = false,
    val readAt: Long? = null,
    val createdAt: Long = System.currentTimeMillis()
)

@Serializable
enum class MessageType {
    TEXT,
    IMAGE,
    FIGURE_SHARE,
    TRANSFER_REQUEST,
    SYSTEM
}

@Serializable
data class SharedFigure(
    val figureId: String,
    val figureName: String,
    val figureImage: String,
    val ownerAction: FigureShareAction? = null
)

@Serializable
enum class FigureShareAction {
    SHOW,
    TRANSFER_OFFER,
    SALE_OFFER
}

@Serializable
data class ConversationPreview(
    val conversationId: String,
    val otherUser: ConversationUser,
    val lastMessage: String,
    val lastMessageTime: Long,
    val unreadCount: Int,
    val isOnline: Boolean = false
)

@Serializable
data class ConversationUser(
    val userId: String,
    val displayName: String,
    val photoUrl: String? = null,
    val isVerified: Boolean = false
)

@Serializable
data class BlockedUser(
    val id: String,
    val userId: String,
    val blockedUserId: String,
    val reason: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)
