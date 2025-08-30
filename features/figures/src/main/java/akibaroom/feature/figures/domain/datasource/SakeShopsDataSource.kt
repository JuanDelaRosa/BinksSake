package akibaroom.feature.figures.domain.datasource

import akibaroom.core.utils.json.Result
import akibaroom.feature.figures.data.model.SakeShopEntity

interface SakeShopsDataSource {
    suspend fun fetchSakeShops() : Result<List<SakeShopEntity>>
}
