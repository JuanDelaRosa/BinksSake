package binkssake.feature.stores.data.mapper

import binksake.feature.stores.api.model.SakeShop
import binkssake.feature.stores.data.model.SakeShopEntity

internal fun SakeShopEntity.toDomain(): SakeShop? {
    val lat = coordinates?.getOrNull(0)
    val lng = coordinates?.getOrNull(1)

    if (name == null || description == null || rating == null || address == null || lat == null ||
        lng == null || googleMapsLink == null || website == null) return null

    return SakeShop(
        name = name,
        description = description,
        picture = picture,
        rating = rating,
        address = address,
        coordinates = Pair(lat, lng),
        googleMapsLink = googleMapsLink,
        website = website
    )
}
