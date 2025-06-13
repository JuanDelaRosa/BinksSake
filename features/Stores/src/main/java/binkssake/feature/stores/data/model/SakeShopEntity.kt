package binkssake.feature.stores.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class SakeShopEntity(
    val name: String? = null,
    val description: String? = null,
    val picture: String? = null,
    val rating: Double? = null,
    val address: String? = null,
    val coordinates: List<Double>? = null,
    @SerialName("google_maps_link") val googleMapsLink: String? = null,
    val website: String? = null
)
