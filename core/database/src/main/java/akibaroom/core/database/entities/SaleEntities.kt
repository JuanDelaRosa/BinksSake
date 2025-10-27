package akibaroom.core.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "sales",
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
        Index("customerId"),
        Index("status"),
        Index("createdAt")
    ]
)
data class SaleEntity(
    @PrimaryKey val id: String,
    val storeId: String,
    val customerId: String?,
    val customerName: String?,
    val itemsJson: String,
    val subtotal: Double,
    val tax: Double,
    val discount: Double,
    val total: Double,
    val paymentMethod: String,
    val status: String,
    val notes: String?,
    val receiptSent: Boolean,
    val receiptEmail: String?,
    val receiptPhone: String?,
    val createdAt: Long,
    val updatedAt: Long
)

@Entity(
    tableName = "sale_items",
    foreignKeys = [
        ForeignKey(
            entity = SaleEntity::class,
            parentColumns = ["id"],
            childColumns = ["saleId"],
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
        Index("saleId"),
        Index("figureId")
    ]
)
data class SaleItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val saleId: String,
    val figureId: String,
    val figureName: String,
    val quantity: Int,
    val unitPrice: Double,
    val subtotal: Double
)
