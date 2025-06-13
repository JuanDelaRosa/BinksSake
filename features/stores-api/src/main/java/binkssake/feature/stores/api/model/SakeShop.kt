package binkssake.feature.stores.api.model

data class SakeShop(
    val name: String,
    val description: String,
    val picture: String?,
    val rating: Double,
    val address: String,
    val googleMapsLink: String,
    val website: String
)
