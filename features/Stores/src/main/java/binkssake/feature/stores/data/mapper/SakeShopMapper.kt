package binkssake.feature.stores.data.mapper

import binkssake.feature.stores.api.model.SakeShop
import binkssake.feature.stores.data.model.SakeShopEntity

internal fun SakeShopEntity.toDomain(): SakeShop? {
    if (name == null || description == null || rating == null || address == null ||
        googleMapsLink == null || website == null) return null

    return SakeShop(
        name = name,
        description = description,
        picture = picture,
        rating = rating,
        address = address,
        googleMapsLink = googleMapsLink,
        website = website
    )
}
