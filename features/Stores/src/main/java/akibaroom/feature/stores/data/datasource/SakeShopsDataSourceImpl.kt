package akibaroom.feature.stores.data.datasource

import akibaroom.core.utils.json.safeJsonParse
import akibaroom.feature.stores.data.SakeShopJsonData
import akibaroom.feature.stores.data.model.SakeShopEntity
import akibaroom.feature.stores.domain.datasource.SakeShopsDataSource

internal class SakeShopsDataSourceImpl(
    private val api: SakeShopJsonData = SakeShopJsonData()
) : SakeShopsDataSource {

    override suspend fun fetchSakeShops() =
        safeJsonParse<List<SakeShopEntity>> {
            api.fetchSakeShopsJson()
        }
}
