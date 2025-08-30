package akibaroom.feature.figures.data.datasource

import akibaroom.core.utils.json.safeJsonParse
import akibaroom.feature.figures.data.SakeShopJsonData
import akibaroom.feature.figures.data.model.SakeShopEntity
import akibaroom.feature.figures.domain.datasource.SakeShopsDataSource

class SakeShopsDataSourceImpl(
    private val api: SakeShopJsonData = SakeShopJsonData()
) : SakeShopsDataSource {

    override suspend fun fetchSakeShops() =
        safeJsonParse<List<SakeShopEntity>> {
            api.fetchSakeShopsJson()
        }
}
