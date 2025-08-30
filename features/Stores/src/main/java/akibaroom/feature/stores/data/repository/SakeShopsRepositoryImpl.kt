package akibaroom.feature.stores.data.repository

import akibaroom.core.utils.json.Result
import akibaroom.feature.stores.data.datasource.SakeShopsDataSourceImpl
import akibaroom.feature.stores.data.mapper.toDomain
import akibaroom.feature.stores.domain.datasource.SakeShopsDataSource
import akibaroom.feature.stores.domain.repository.SakeShopsRepository

class SakeShopsRepositoryImpl(
    private val dataSource: SakeShopsDataSource = SakeShopsDataSourceImpl()
) : SakeShopsRepository {
    override suspend fun fetchSakeShops() = when (val result = dataSource.fetchSakeShops()) {
        is Result.Success -> {
            val mapResult = result.data.mapNotNull {
                it.toDomain()
            }
            Result.Success(mapResult)
        }
        is Result.Error -> Result.Error(Error("Failed to fetch sake shops"))
    }
}
