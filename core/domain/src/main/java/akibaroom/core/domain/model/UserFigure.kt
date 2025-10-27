package akibaroom.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserFigure(
    val id: String,
    val figureId: String,
    val userId: String,
    val status: FigureStatus,
    val purchasePrice: Double? = null,
    val purchaseDate: String? = null,
    val purchaseLocation: String? = null,
    val storeId: String? = null,
    val condition: Condition = Condition.MINT,
    val hasBox: Boolean = true,
    val boxCondition: Condition? = null,
    val isSigned: Boolean = false,
    val signedBy: String? = null,
    val notes: String? = null,
    val customImages: List<String> = emptyList(),
    val displayLocation: String? = null,
    val rating: Int? = null,
    val addedAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

@Serializable
enum class FigureStatus {
    OWNED,
    WISHLIST,
    PREORDER,
    SOLD,
    TRADED,
    GIFTED
}

@Serializable
enum class Condition {
    MINT,
    NEAR_MINT,
    GOOD,
    FAIR,
    POOR,
    DAMAGED
}
