package akibaroom.feature.stores.domain.repository

import akibaroom.core.utils.json.Result
import akibaroom.feature.stores.api.model.SakeShop

internal interface SakeShopsRepository {
    suspend fun fetchSakeShops() : Result<List<SakeShop>>
}
