package akibaroom.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Store(
    val id: String,
    val ownerId: String,
    val name: String,
    val description: String,
    val logoUrl: String? = null,
    val bannerUrl: String? = null,
    val address: Address,
    val contact: StoreContact,
    val hours: StoreHours? = null,
    val policies: StorePolicies = StorePolicies(),
    val rating: StoreRating = StoreRating(),
    val isVerified: Boolean = false,
    val gallery: List<String> = emptyList(),
    val socialLinks: SocialLinks = SocialLinks(),
    val createdAt: Long = System.currentTimeMillis()
)

@Serializable
data class Address(
    val street: String,
    val city: String,
    val state: String,
    val country: String,
    val postalCode: String,
    val latitude: Double? = null,
    val longitude: Double? = null
)

@Serializable
data class StoreContact(
    val phone: String,
    val whatsapp: String? = null,
    val email: String,
    val website: String? = null
)

@Serializable
data class StoreHours(
    val monday: DayHours? = null,
    val tuesday: DayHours? = null,
    val wednesday: DayHours? = null,
    val thursday: DayHours? = null,
    val friday: DayHours? = null,
    val saturday: DayHours? = null,
    val sunday: DayHours? = null
)

@Serializable
data class DayHours(
    val open: String,
    val close: String,
    val isClosed: Boolean = false
)

@Serializable
data class StorePolicies(
    val returnsAccepted: Boolean = false,
    val returnDays: Int = 0,
    val shippingAvailable: Boolean = false,
    val pickupAvailable: Boolean = true,
    val termsAndConditions: String? = null
)

@Serializable
data class StoreRating(
    val averageRating: Double = 0.0,
    val totalReviews: Int = 0,
    val fiveStars: Int = 0,
    val fourStars: Int = 0,
    val threeStars: Int = 0,
    val twoStars: Int = 0,
    val oneStar: Int = 0
)

@Serializable
data class StoreInventoryItem(
    val id: String,
    val storeId: String,
    val figureId: String,
    val quantity: Int,
    val purchasePrice: Double,
    val salePrice: Double,
    val margin: Double,
    val supplier: String? = null,
    val sku: String? = null,
    val location: String? = null,
    val lowStockThreshold: Int = 5,
    val addedAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

@Serializable
data class PreorderItem(
    val id: String,
    val storeId: String,
    val figureId: String,
    val quantityToOrder: Int,
    val quantityReserved: Int = 0,
    val supplier: String? = null,
    val supplierPrice: Double,
    val expectedSalePrice: Double,
    val estimatedArrivalDate: String? = null,
    val depositRequired: Double? = null,
    val depositPercentage: Int = 0,
    val status: PreorderStatus = PreorderStatus.PLANNED,
    val notes: String? = null,
    val interestedCustomers: List<String> = emptyList(),
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

@Serializable
enum class PreorderStatus {
    PLANNED,
    ORDERED,
    IN_TRANSIT,
    ARRIVED,
    CANCELLED
}
