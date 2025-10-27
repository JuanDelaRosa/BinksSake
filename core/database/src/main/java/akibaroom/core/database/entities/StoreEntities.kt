package akibaroom.core.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "stores",
    indices = [Index("ownerId")]
)
data class StoreEntity(
    @PrimaryKey val id: String,
    val ownerId: String,
    val name: String,
    val description: String,
    val logoUrl: String?,
    val bannerUrl: String?,
    val addressJson: String,
    val contactJson: String,
    val hoursJson: String?,
    val policiesJson: String,
    val ratingJson: String,
    val isVerified: Boolean,
    val gallery: String,
    val socialLinksJson: String,
    val createdAt: Long
)

@Entity(
    tableName = "store_inventory",
    foreignKeys = [
        ForeignKey(
            entity = StoreEntity::class,
            parentColumns = ["id"],
            childColumns = ["storeId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = FigureEntity::class,
            parentColumns = ["id"],
            childColumns = ["figureId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("storeId"),
        Index("figureId"),
        Index("quantity")
    ]
)
data class StoreInventoryEntity(
    @PrimaryKey val id: String,
    val storeId: String,
    val figureId: String,
    val quantity: Int,
    val purchasePrice: Double,
    val salePrice: Double,
    val margin: Double,
    val supplier: String?,
    val sku: String?,
    val location: String?,
    val lowStockThreshold: Int,
    val addedAt: Long,
    val updatedAt: Long
)

@Entity(
    tableName = "preorders",
    foreignKeys = [
        ForeignKey(
            entity = StoreEntity::class,
            parentColumns = ["id"],
            childColumns = ["storeId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = FigureEntity::class,
            parentColumns = ["id"],
            childColumns = ["figureId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("storeId"),
        Index("figureId"),
        Index("status")
    ]
)
data class PreorderEntity(
    @PrimaryKey val id: String,
    val storeId: String,
    val figureId: String,
    val quantityToOrder: Int,
    val quantityReserved: Int,
    val supplier: String?,
    val supplierPrice: Double,
    val expectedSalePrice: Double,
    val estimatedArrivalDate: String?,
    val depositRequired: Double?,
    val depositPercentage: Int,
    val status: String,
    val notes: String?,
    val interestedCustomers: String,
    val createdAt: Long,
    val updatedAt: Long
)

@Entity(
    tableName = "customers",
    foreignKeys = [
        ForeignKey(
            entity = StoreEntity::class,
            parentColumns = ["id"],
            childColumns = ["storeId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("storeId"),
        Index("userId"),
        Index("isFrequent")
    ]
)
data class CustomerEntity(
    @PrimaryKey val id: String,
    val storeId: String,
    val userId: String?,
    val name: String,
    val email: String?,
    val phone: String?,
    val totalPurchases: Double,
    val purchaseCount: Int,
    val lastPurchaseAt: Long?,
    val isFrequent: Boolean,
    val notes: String?,
    val createdAt: Long
)
