package binkssake.feature.stores.domain.datasource

import binkssake.core.utils.json.Result
import binkssake.feature.stores.data.model.SakeShopEntity

internal interface SakeShopsDataSource {
    suspend fun fetchSakeShops() : Result<List<SakeShopEntity>>
}
