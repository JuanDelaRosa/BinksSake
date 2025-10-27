package akibaroom.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Figure(
    val id: String,
    val name: String,
    val manufacturer: String,
    val series: String,
    val character: String,
    val category: FigureCategory,
    val scale: String? = null,
    val releaseDate: String? = null,
    val msrp: Double? = null,
    val barcode: String? = null,
    val jan: String? = null,
    val images: List<String> = emptyList(),
    val description: String = "",
    val isNSFW: Boolean = false,
    val uploadedBy: String,
    val uploadedAt: Long = System.currentTimeMillis(),
    val tags: List<String> = emptyList(),
    val dimensions: Dimensions? = null,
    val weight: Double? = null,
    val material: String? = null,
    val sculptor: String? = null,
    val illustrator: String? = null,
    val designer: String? = null,
    val colorProducer: String? = null,
    val copyright: String? = null,
    val version: String? = null,
    val origin: String? = null,
    val classification: String? = null,
    val releaseInfo: ReleaseInfo? = null,
    val externalIds: ExternalIds = ExternalIds(),
    val metadata: Map<String, String> = emptyMap()
)

@Serializable
data class Dimensions(
    val height: Double,
    val width: Double,
    val depth: Double,
    val unit: MeasurementUnit = MeasurementUnit.CM
)

@Serializable
data class ReleaseInfo(
    val releaseDate: String? = null,
    val rereleaseDate: String? = null,
    val country: String? = null,
    val price: Double? = null,
    val currency: Currency = Currency.JPY,
    val preorderStart: String? = null,
    val preorderEnd: String? = null
)

@Serializable
data class ExternalIds(
    val mfcId: String? = null,
    val amiAmiId: String? = null,
    val hobbySearchId: String? = null,
    val goodSmileId: String? = null,
    val hlkjId: String? = null,
    val tokyoOtakuModeId: String? = null,
    val jan: String? = null,
    val ean: String? = null,
    val upc: String? = null
)

@Serializable
enum class FigureCategory {
    SCALE_FIGURE,
    NENDOROID,
    FIGMA,
    PRIZE_FIGURE,
    STATUE,
    ACTION_FIGURE,
    PLUSH,
    DOLL,
    MODEL_KIT,
    GOODS,
    GARAGE_KIT,
    TRADING_FIGURE,
    CHIBI_FIGURE,
    CAST_OFF,
    OTHER
}

@Serializable
enum class MeasurementUnit {
    CM,
    INCH,
    MM
}

@Serializable
enum class Currency {
    JPY,
    USD,
    EUR,
    CNY,
    KRW,
    TWD,
    HKD,
    SGD,
    AUD,
    CAD,
    GBP,
    CHF,
    SEK,
    NOK,
    DKK
}
