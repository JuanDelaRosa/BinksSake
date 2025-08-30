package akibaroom.feature.figures.data.repository

import akibaroom.core.utils.json.Result
import akibaroom.feature.figures.data.datasource.SakeShopsDataSourceImpl
import akibaroom.feature.figures.data.mapper.toDomain
import akibaroom.feature.figures.domain.datasource.SakeShopsDataSource
import akibaroom.feature.figures.domain.repository.SakeShopsRepository

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
