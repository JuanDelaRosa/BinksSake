package akibaroom.feature.figures.data.repository

import akibaroom.core.utils.json.Result
import akibaroom.feature.figures.domain.datasource.FigureDataSource
import akibaroom.feature.figures.domain.repository.FigureRepository
import javax.inject.Inject

class FigureRepositoryImpl @Inject constructor(
    private val dataSource: FigureDataSource
) : FigureRepository {
    override suspend fun fetchFigures() = when (val result = dataSource.fetchFigures()) {
        is Result.Success -> Result.Success(result.data)
        is Result.Error -> Result.Error(Error("Failed to fetch figures"))
    }
}
