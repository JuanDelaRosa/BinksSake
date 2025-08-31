package akibaroom.feature.figures.data.datasource

import akibaroom.core.utils.json.Result
import akibaroom.feature.figures.api.RickAndMortyApi
import akibaroom.feature.figures.data.mapper.toUi
import akibaroom.feature.figures.domain.datasource.FigureDataSource
import akibaroom.feature.figures.ui.FigureUi
import javax.inject.Inject

class FigureDataSourceImpl @Inject constructor(
    private val api: RickAndMortyApi
) : FigureDataSource {

    override suspend fun fetchFigures(): Result<List<FigureUi>> {
        return try {
            val page = api.getCharacters(page = 1)
            Result.Success(page.results.map { it.toUi() })
        } catch (t: Throwable) {
            Result.Error(t)
        }
    }
}
