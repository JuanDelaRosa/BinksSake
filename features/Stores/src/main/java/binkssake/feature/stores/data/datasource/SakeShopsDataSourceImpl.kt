package binkssake.feature.stores.data.datasource

import binkssake.core.utils.json.safeJsonParse
import binkssake.feature.stores.data.SakeShopJsonData
import binkssake.feature.stores.data.model.SakeShopEntity
import binkssake.feature.stores.domain.datasource.SakeShopsDataSource

internal class SakeShopsDataSourceImpl(
    private val api: SakeShopJsonData = SakeShopJsonData()
) : SakeShopsDataSource {

    override suspend fun fetchSakeShops() =
        safeJsonParse<List<SakeShopEntity>> {
            api.fetchSakeShopsJson()
        }
}
