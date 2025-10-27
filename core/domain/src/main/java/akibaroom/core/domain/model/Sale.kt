package akibaroom.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Sale(
    val id: String,
    val storeId: String,
    val customerId: String? = null,
    val customerName: String? = null,
    val items: List<SaleItem>,
    val subtotal: Double,
    val tax: Double,
    val discount: Double = 0.0,
    val total: Double,
    val paymentMethod: PaymentMethod,
    val status: SaleStatus = SaleStatus.COMPLETED,
    val notes: String? = null,
    val receiptSent: Boolean = false,
    val receiptEmail: String? = null,
    val receiptPhone: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

@Serializable
data class SaleItem(
    val figureId: String,
    val figureName: String,
    val quantity: Int,
    val unitPrice: Double,
    val subtotal: Double
)

@Serializable
enum class SaleStatus {
    IN_PROGRESS,
    COMPLETED,
    CANCELLED,
    REFUNDED
}

@Serializable
enum class PaymentMethod {
    CASH,
    CARD,
    TRANSFER,
    PAYPAL,
    MERCADO_PAGO,
    OTHER
}

@Serializable
data class Customer(
    val id: String,
    val storeId: String,
    val userId: String? = null,
    val name: String,
    val email: String? = null,
    val phone: String? = null,
    val totalPurchases: Double = 0.0,
    val purchaseCount: Int = 0,
    val lastPurchaseAt: Long? = null,
    val isFrequent: Boolean = false,
    val notes: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)

@Serializable
data class StoreAnalytics(
    val storeId: String,
    val period: AnalyticsPeriod,
    val totalSales: Double,
    val totalOrders: Int,
    val averageOrderValue: Double,
    val topSellingFigures: List<TopSellingItem>,
    val topCategories: List<CategorySales>,
    val salesByDay: Map<String, Double>,
    val frequentCustomers: List<CustomerPurchaseInfo>,
    val revenue: Double,
    val profit: Double,
    val profitMargin: Double,
    val generatedAt: Long = System.currentTimeMillis()
)

@Serializable
data class TopSellingItem(
    val figureId: String,
    val figureName: String,
    val quantitySold: Int,
    val revenue: Double
)

@Serializable
data class CategorySales(
    val category: FigureCategory,
    val totalSales: Double,
    val quantitySold: Int
)

@Serializable
data class CustomerPurchaseInfo(
    val customerId: String,
    val customerName: String,
    val totalSpent: Double,
    val purchaseCount: Int
)

@Serializable
enum class AnalyticsPeriod {
    TODAY,
    WEEK,
    MONTH,
    QUARTER,
    YEAR,
    CUSTOM
}
