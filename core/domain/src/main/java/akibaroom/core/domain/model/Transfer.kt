package akibaroom.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class FigureTransfer(
    val id: String,
    val figureId: String,
    val userFigureId: String,
    val fromUserId: String,
    val fromUserName: String,
    val toUserId: String,
    val toUserName: String,
    val status: TransferStatus,
    val message: String? = null,
    val requestedAt: Long = System.currentTimeMillis(),
    val confirmedByRecipientAt: Long? = null,
    val completedAt: Long? = null,
    val cancelledAt: Long? = null,
    val cancellationReason: String? = null
)

@Serializable
enum class TransferStatus {
    PENDING_RECIPIENT_CONFIRMATION,
    ACCEPTED,
    COMPLETED,
    DECLINED,
    CANCELLED
}

@Serializable
data class FigureOwnershipHistory(
    val id: String,
    val figureId: String,
    val userId: String,
    val userName: String,
    val acquiredFrom: AcquisitionSource,
    val previousOwnerId: String? = null,
    val previousOwnerName: String? = null,
    val purchasePrice: Double? = null,
    val notes: String? = null,
    val acquiredAt: Long,
    val releasedAt: Long? = null
)

@Serializable
enum class AcquisitionSource {
    PURCHASED_STORE,
    PURCHASED_USER,
    TRANSFERRED,
    GIFT,
    TRADE,
    ORIGINAL_OWNER
}
