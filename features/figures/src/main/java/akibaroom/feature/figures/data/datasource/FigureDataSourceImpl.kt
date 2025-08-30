package akibaroom.feature.figures.data.datasource

import akibaroom.core.utils.json.Result
import akibaroom.feature.figures.api.CharacterResponse
import akibaroom.feature.figures.api.RickAndMortyApi
import akibaroom.feature.figures.domain.datasource.FigureDataSource
import javax.inject.Inject

class FigureDataSourceImpl @Inject constructor(
    private val api: RickAndMortyApi
) : FigureDataSource {

    override suspend fun fetchFigures(): Result<List<CharacterResponse>> {
        return try {
            val page = api.getCharacters(page = 1)
            Result.Success(page.results)
        } catch (t: Throwable) {
            Result.Error(t)
        }
    }
}
