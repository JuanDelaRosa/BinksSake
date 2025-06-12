package binkssake.feature.stores.domain.repository

import binkssake.core.utils.json.Result
import binksake.feature.stores.api.model.SakeShop

internal interface SakeShopsRepository {
    suspend fun fetchSakeShops() : Result<SakeShop>
}
