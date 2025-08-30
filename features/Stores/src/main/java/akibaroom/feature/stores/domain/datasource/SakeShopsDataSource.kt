package akibaroom.feature.stores.domain.datasource

import akibaroom.core.utils.json.Result
import akibaroom.feature.stores.data.model.SakeShopEntity

internal interface SakeShopsDataSource {
    suspend fun fetchSakeShops() : Result<List<SakeShopEntity>>
}
