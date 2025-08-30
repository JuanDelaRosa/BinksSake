package akibaroom.feature.figures.domain.repository

import akibaroom.core.utils.json.Result
import akibaroom.feature.figures.api.model.SakeShop

interface SakeShopsRepository {
    suspend fun fetchSakeShops(): Result<List<SakeShop>>
}
