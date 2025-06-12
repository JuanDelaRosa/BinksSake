package binkssake.feature.stores.domain.repository

import binkssake.core.utils.json.Result
import binkssake.feature.stores.api.model.SakeShop

internal interface SakeShopsRepository {
    suspend fun fetchSakeShops() : Result<List<SakeShop>>
}
