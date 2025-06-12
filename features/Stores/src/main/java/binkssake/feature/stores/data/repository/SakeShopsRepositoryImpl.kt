package binkssake.feature.stores.data.repository

import binkssake.core.utils.json.Result
import binkssake.feature.stores.data.datasource.SakeShopsDataSourceImpl
import binkssake.feature.stores.data.mapper.toDomain
import binkssake.feature.stores.domain.datasource.SakeShopsDataSource
import binkssake.feature.stores.domain.repository.SakeShopsRepository

internal class SakeShopsRepositoryImpl(
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
