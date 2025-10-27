package akibaroom.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class MfcFigure(
    val itemId: String,
    val name: String,
    val origin: String? = null,
    val character: String? = null,
    val version: String? = null,
    val company: String,
    val classification: String? = null,
    val sculptedBy: String? = null,
    val illustratedBy: String? = null,
    val designedBy: String? = null,
    val colorProductionBy: String? = null,
    val material: String? = null,
    val scale: String? = null,
    val releaseDate: String? = null,
    val releasePrice: Double? = null,
    val country: String? = null,
    val jan: String? = null,
    val dimensions: MfcDimensions? = null,
    val weight: Double? = null,
    val images: List<String> = emptyList(),
    val barcode: String? = null,
    val category: String? = null,
    val copyright: String? = null
)

@Serializable
data class MfcDimensions(
    val height: String? = null,
    val width: String? = null,
    val depth: String? = null
)

@Serializable
data class MfcSearchResult(
    val items: List<MfcSearchItem>,
    val total: Int,
    val page: Int,
    val perPage: Int
)

@Serializable
data class MfcSearchItem(
    val itemId: String,
    val name: String,
    val imageUrl: String? = null,
    val manufacturer: String,
    val category: String,
    val releaseDate: String? = null
)

@Serializable
data class MfcImportRequest(
    val mfcUrl: String,
    val userId: String,
    val addToCollection: Boolean = true,
    val addToWishlist: Boolean = false
)

@Serializable
data class MfcImportResult(
    val success: Boolean,
    val figure: Figure? = null,
    val userFigure: UserFigure? = null,
    val error: String? = null,
    val warnings: List<String> = emptyList()
)

@Serializable
data class ExternalStoreAvailability(
    val figureId: String,
    val stores: List<StoreAvailability>
)

@Serializable
data class StoreAvailability(
    val storeName: String,
    val storeUrl: String,
    val price: Double? = null,
    val currency: String,
    val inStock: Boolean,
    val isPreorder: Boolean = false,
    val estimatedReleaseDate: String? = null,
    val lastChecked: Long = System.currentTimeMillis()
)

enum class ExternalStore {
    AMIAMI,
    HOBBY_SEARCH,
    GOOD_SMILE,
    TOKYO_OTAKU_MODE,
    SOLARIS_JAPAN,
    PLAY_ASIA,
    CDJ,
    MERCARI_JP,
    YAHOO_AUCTIONS_JP,
    MANDARAKE,
    SURUGAYA,
    LASHINBANG,
    RAKUTEN,
    AMAZON_JP
}

@Serializable
data class BarcodeSearchRequest(
    val barcode: String,
    val searchExternal: Boolean = true
)

@Serializable
data class BarcodeSearchResult(
    val barcode: String,
    val foundInDatabase: Boolean,
    val figure: Figure? = null,
    val externalResults: List<ExternalSearchResult> = emptyList()
)

@Serializable
data class ExternalSearchResult(
    val source: String,
    val data: MfcFigure? = null,
    val confidence: Double,
    val url: String? = null
)
